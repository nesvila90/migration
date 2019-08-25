
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
public class FilesScanned implements Serializable
{

    @JsonProperty("Count")
    private Long count;
    @JsonProperty("Rate")
    private Double rate;
    private final static long serialVersionUID = 8390199139373217237L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FilesScanned() {
    }

    /**
     * 
     * @param rate
     * @param count
     */
    public FilesScanned(Long count, Double rate) {
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
