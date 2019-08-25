
package co.gov.sic.migration.adapters.alfresco.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Count",
    "Rate"
})
public class BytesImported implements Serializable
{

    @JsonProperty("Count")
    private Long count;
    @JsonProperty("Rate")
    private Double rate;
    private final static long serialVersionUID = 3876132446172399594L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BytesImported() {
    }

    /**
     * 
     * @param rate
     * @param count
     */
    public BytesImported(Long count, Double rate) {
        super();
        this.count = count;
        this.rate = rate;
    }

    @JsonProperty("Count")
    public Long getCount() {
        return count;
    }

    @JsonProperty("Count")
    public void setCount(Long count) {
        this.count = count;
    }

    @JsonProperty("Rate")
    public Double getRate() {
        return rate;
    }

    @JsonProperty("Rate")
    public void setRate(Double rate) {
        this.rate = rate;
    }


}
