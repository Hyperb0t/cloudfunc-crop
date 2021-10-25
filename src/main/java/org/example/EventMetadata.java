
package org.example;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EventMetadata {

    private String event_id;
    private String event_type;
    private String created_at;
    private TracingContext tracing_context;
    private String cloud_id;
    private String folder_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public EventMetadata() {
    }

    /**
     * 
     * @param eventId
     * @param createdAt
     * @param tracingContext
     * @param cloudId
     * @param eventType
     * @param folderId
     */
    public EventMetadata(String eventId, String eventType, String createdAt, TracingContext tracingContext, String cloudId, String folderId) {
        super();
        this.event_id = eventId;
        this.event_type = eventType;
        this.created_at = createdAt;
        this.tracing_context = tracingContext;
        this.cloud_id = cloudId;
        this.folder_id = folderId;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public TracingContext getTracing_context() {
        return tracing_context;
    }

    public void setTracing_context(TracingContext tracing_context) {
        this.tracing_context = tracing_context;
    }

    public String getCloud_id() {
        return cloud_id;
    }

    public void setCloud_id(String cloud_id) {
        this.cloud_id = cloud_id;
    }

    public String getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(String folder_id) {
        this.folder_id = folder_id;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EventMetadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("eventId");
        sb.append('=');
        sb.append(((this.event_id == null)?"<null>":this.event_id));
        sb.append(',');
        sb.append("eventType");
        sb.append('=');
        sb.append(((this.event_type == null)?"<null>":this.event_type));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.created_at == null)?"<null>":this.created_at));
        sb.append(',');
        sb.append("tracingContext");
        sb.append('=');
        sb.append(((this.tracing_context == null)?"<null>":this.tracing_context));
        sb.append(',');
        sb.append("cloudId");
        sb.append('=');
        sb.append(((this.cloud_id == null)?"<null>":this.cloud_id));
        sb.append(',');
        sb.append("folderId");
        sb.append('=');
        sb.append(((this.folder_id == null)?"<null>":this.folder_id));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.event_id == null)? 0 :this.event_id.hashCode()));
        result = ((result* 31)+((this.created_at == null)? 0 :this.created_at.hashCode()));
        result = ((result* 31)+((this.tracing_context == null)? 0 :this.tracing_context.hashCode()));
        result = ((result* 31)+((this.cloud_id == null)? 0 :this.cloud_id.hashCode()));
        result = ((result* 31)+((this.event_type == null)? 0 :this.event_type.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.folder_id == null)? 0 :this.folder_id.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EventMetadata) == false) {
            return false;
        }
        EventMetadata rhs = ((EventMetadata) other);
        return ((((((((this.event_id == rhs.event_id)||((this.event_id != null)&&this.event_id.equals(rhs.event_id)))&&((this.created_at == rhs.created_at)||((this.created_at != null)&&this.created_at.equals(rhs.created_at))))&&((this.tracing_context == rhs.tracing_context)||((this.tracing_context != null)&&this.tracing_context.equals(rhs.tracing_context))))&&((this.cloud_id == rhs.cloud_id)||((this.cloud_id != null)&&this.cloud_id.equals(rhs.cloud_id))))&&((this.event_type == rhs.event_type)||((this.event_type != null)&&this.event_type.equals(rhs.event_type))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.folder_id == rhs.folder_id)||((this.folder_id != null)&&this.folder_id.equals(rhs.folder_id))));
    }

}
