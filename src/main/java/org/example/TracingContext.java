
package org.example;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class TracingContext {

    private String trace_id;
    private String span_id;
    private String parent_span_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TracingContext() {
    }

    /**
     * 
     * @param traceId
     * @param spanId
     * @param parentSpanId
     */
    public TracingContext(String traceId, String spanId, String parentSpanId) {
        super();
        this.trace_id = traceId;
        this.span_id = spanId;
        this.parent_span_id = parentSpanId;
    }

    public String getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }

    public String getSpan_id() {
        return span_id;
    }

    public void setSpan_id(String span_id) {
        this.span_id = span_id;
    }

    public String getParent_span_id() {
        return parent_span_id;
    }

    public void setParent_span_id(String parent_span_id) {
        this.parent_span_id = parent_span_id;
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
        sb.append(TracingContext.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.trace_id == null)?"<null>":this.trace_id));
        sb.append(',');
        sb.append("spanId");
        sb.append('=');
        sb.append(((this.span_id == null)?"<null>":this.span_id));
        sb.append(',');
        sb.append("parentSpanId");
        sb.append('=');
        sb.append(((this.parent_span_id == null)?"<null>":this.parent_span_id));
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
        result = ((result* 31)+((this.trace_id == null)? 0 :this.trace_id.hashCode()));
        result = ((result* 31)+((this.span_id == null)? 0 :this.span_id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.parent_span_id == null)? 0 :this.parent_span_id.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TracingContext) == false) {
            return false;
        }
        TracingContext rhs = ((TracingContext) other);
        return (((((this.trace_id == rhs.trace_id)||((this.trace_id != null)&&this.trace_id.equals(rhs.trace_id)))&&((this.span_id == rhs.span_id)||((this.span_id != null)&&this.span_id.equals(rhs.span_id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.parent_span_id == rhs.parent_span_id)||((this.parent_span_id != null)&&this.parent_span_id.equals(rhs.parent_span_id))));
    }

}
