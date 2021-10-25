# cloudfunc-crop
Это исходный код облачной функции (для яндекс облака).
Чтобы создать функцию, запакуйте файл pom.xml и папку src в zip архив и загрузите его в созданную функцию.
Подразумевается, что функция будет тригером на загрузку файла в облачное хранилище.

Название бакета жестко закреплено в коде и равно строке "Cloudphoto16".

Данные для авторизации хранятся в переменных окружения:
* aws_access_key_id
* aws_secret_access_key
* vision_api_key

После того, как вы загрузите фото в объектное хранилище, лица на нем будут распознаны с помощью Яндекс vision.
Координаты расположения лиц на фотографии будут получены, и по ним из оригинального вырежутся новые изображения только с лицами.
Эти новые вырезанные изображения будут сохранены в объектное хранилище с ключом "%primaryImageKey%/face0.png".

Ключи созданных изображений отправляются в очередь сообщений.
Url очереди жестко закреплен в коде.
Сама очередь уже создана в моем облачном каталоге и имеет имя "d16-result".

Лучше не использовать функцию для обработки изображений более 1мб.
Яндекс vision не обрабатывает файлы больше этого размера.
Также java код может выдать out of memory при вырезании изображения, Т.к. функции выделено только 128мб памяти.
Это происходит из-за высокого разрешения изображений.
Например, фотография 6000x4000 точно выдаст эту ошибку.


# English version
This is the source code for (yandex) cloud function.
To create a function from this code, package pom.xml and src folder to zip archive and upload this archive as a function. 
This function is supposed to be a trigger for file upload to object storage.

Bucket name, which is used to download and upload pictures is hard-coded. It is a string "Cloudphoto16".

Credentials are stored as environment variables:
* aws_access_key_id
* aws_secret_access_key
* vision_api_key

After you upload a photo to the bucket, faces are recognized with yandex vision service.
Coordinates of faces are recieved and new images containing only faces are cropped out of primary image.
Images of faces are saved to object storage with key "%primaryImageKey%/face0.png".

Keys of newly saved images are sent to message queue.
Its url is also hard-coded.
Queue is already created in my cloud catalog and its name is "d16-result".

Please, do not use this function with photos larger than 1mb.
Yandex vision does not process files larger than 1mb.
Also, java code for cropping image can throw out of memory exception, because cloud functions are given only 128mb of memory.
And photo resolution nowaday can be rather high. 
For example you will definitely get out of memory error while uploading 6000x4000 image.
