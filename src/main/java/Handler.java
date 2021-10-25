import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Event;
import yandex.cloud.sdk.functions.Context;
import yandex.cloud.sdk.functions.YcFunction;

import javax.imageio.ImageIO;


public class Handler implements YcFunction<String, String> {
    @Override
    public String handle(String eventJson, Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Event event = null;
        try {
            event = mapper.readValue(eventJson, Event.class);
        } catch (Exception e) {
            System.err.println("can't deserialize event json");
            e.printStackTrace();
        }
        String id = System.getenv("aws_access_key_id");
        String secret = System.getenv("aws_secret_access_key");
        AmazonS3 amazonS3 = null;
        try {
            amazonS3 = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(id, secret)))
                    .withEndpointConfiguration(
                            new AmazonS3ClientBuilder.EndpointConfiguration(
                                    "storage.yandexcloud.net", "ru-central1"
                            )
                    )
                    .build();
        } catch (Exception e) {
            System.err.println("can't create s3 instance");
            e.printStackTrace();
        }

        String objectId = null;
        String bucketName = null;
        try {
            objectId = event.getMessages().get(0).getDetails().getObject_id();
            bucketName = event.getMessages().get(0).getDetails().getBucket_id();
        } catch (Exception e) {
            System.err.println("can't get object id from eventJson");
            e.printStackTrace();
        }
        if (objectId.matches("^.*/face[0-9]{1}.png$")) {
            return "ok";
        }
        BufferedImage image = null;
        byte[] imageBytes = null;
        try {
            imageBytes = amazonS3.getObject("cloudphoto16", objectId).getObjectContent().readAllBytes();
            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (Exception e) {
            System.err.println("can't read image from cloud object");
        }
        VisionApi visionApi = new VisionApi();
        String recognitionResult = null;
        String visionApiKey = System.getenv("vision_api_key");
        try {
            recognitionResult = visionApi.recognizeFaces(imageBytes, "Api-Key " + visionApiKey);
        } catch (Exception e) {
            System.err.println("can't get result from vision to recognize faces");
            e.printStackTrace();
        }

        ResponseParser responseParser = new ResponseParser();
        System.out.println(recognitionResult);
        List<Rectangle> faceRectangles = responseParser.getFacesRects(recognitionResult);

        String queueUrl = "https://message-queue.api.cloud.yandex.net/b1gs4a51unfsngpt0hke/dj6000000003nafe06dt/d16-result";

        AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(id, secret));
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(provider)
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net", "ru-central1"
                        )).build();

        for(int i = 0; i < faceRectangles.size(); i++) {
            Rectangle r = faceRectangles.get(i);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            BufferedImage subimage = image.getSubimage(r.x, r.y, r.width, r.height);
            try {
                ImageIO.write(subimage, "png", os);
                metadata.setContentLength(os.toByteArray().length);
                String key = objectId + "/face" + i + ".png";
                amazonS3.putObject("cloudphoto16", key,
                        new ByteArrayInputStream(os.toByteArray()),
                        metadata);
                sqs.sendMessage(queueUrl, key);
            } catch (Exception e) {
                System.err.println("can't write parts of image to buffers and upload");
                e.printStackTrace();
            }
        }
        return "ok";
    }

}
