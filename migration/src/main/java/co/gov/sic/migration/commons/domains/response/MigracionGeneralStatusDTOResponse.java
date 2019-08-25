
package co.gov.sic.migration.commons.domains.response;

import co.gov.sic.migration.adapters.alfresco.domain.response.SourceCounters;
import co.gov.sic.migration.adapters.alfresco.domain.response.SourceParameters;
import co.gov.sic.migration.adapters.alfresco.domain.response.TargetCounters;
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
    "processingState",
    "inProgress",
    "neverRun",
    "initiatingUserId",
    "sourceName",
    "sourceParameters",
    "targetPath",
    "stopping",
    "scanning",
    "paused",
    "succeeded",
    "failed",
    "stopped",
    "inPlaceImportPossible",
    "dryRun",
    "startDate",
    "scanEndDate",
    "endDate",
    "scanDurationInNs",
    "scanDuration",
    "durationInNs",
    "duration",
    "batchWeight",
    "queuedBatches",
    "maxQueuedBatches",
    "numberOfActiveThreads",
    "totalNumberOfThreads",
    "sourceCounters",
    "targetCounters"
})
public class MigracionGeneralStatusDTOResponse implements Serializable
{

    @JsonProperty("processingState")
    private String processingState;
    @JsonProperty("inProgress")
    private Boolean inProgress;
    @JsonProperty("neverRun")
    private Boolean neverRun;
    @JsonProperty("initiatingUserId")
    private String initiatingUserId;
    @JsonProperty("sourceName")
    private String sourceName;
    @JsonProperty("sourceParameters")
    private SourceParameters sourceParameters;
    @JsonProperty("targetPath")
    private String targetPath;
    @JsonProperty("stopping")
    private Boolean stopping;
    @JsonProperty("scanning")
    private Boolean scanning;
    @JsonProperty("paused")
    private Boolean paused;
    @JsonProperty("succeeded")
    private Boolean succeeded;
    @JsonProperty("failed")
    private Boolean failed;
    @JsonProperty("stopped")
    private Boolean stopped;
    @JsonProperty("inPlaceImportPossible")
    private Boolean inPlaceImportPossible;
    @JsonProperty("dryRun")
    private Boolean dryRun;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("scanEndDate")
    private String scanEndDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("scanDurationInNs")
    private Long scanDurationInNs;
    @JsonProperty("scanDuration")
    private String scanDuration;
    @JsonProperty("durationInNs")
    private Long durationInNs;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("batchWeight")
    private Long batchWeight;
    @JsonProperty("queuedBatches")
    private Long queuedBatches;
    @JsonProperty("maxQueuedBatches")
    private Long maxQueuedBatches;
    @JsonProperty("numberOfActiveThreads")
    private Long numberOfActiveThreads;
    @JsonProperty("totalNumberOfThreads")
    private Long totalNumberOfThreads;
    @JsonProperty("sourceCounters")
    private SourceCounters sourceCounters;
    @JsonProperty("targetCounters")
    private TargetCounters targetCounters;
    private final static long serialVersionUID = -2918232026795638072L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MigracionGeneralStatusDTOResponse() {
    }

    /**
     * 
     * @param succeeded
     * @param startDate
     * @param failed
     * @param sourceParameters
     * @param scanEndDate
     * @param endDate
     * @param inProgress
     * @param scanDuration
     * @param batchWeight
     * @param maxQueuedBatches
     * @param sourceCounters
     * @param sourceName
     * @param scanDurationInNs
     * @param targetCounters
     * @param dryRun
     * @param inPlaceImportPossible
     * @param queuedBatches
     * @param durationInNs
     * @param scanning
     * @param processingState
     * @param paused
     * @param stopping
     * @param targetPath
     * @param duration
     * @param stopped
     * @param numberOfActiveThreads
     * @param totalNumberOfThreads
     * @param neverRun
     * @param initiatingUserId
     */
    public MigracionGeneralStatusDTOResponse(String processingState, Boolean inProgress, Boolean neverRun, String initiatingUserId, String sourceName, SourceParameters sourceParameters, String targetPath, Boolean stopping, Boolean scanning, Boolean paused, Boolean succeeded, Boolean failed, Boolean stopped, Boolean inPlaceImportPossible, Boolean dryRun, String startDate, String scanEndDate, String endDate, Long scanDurationInNs, String scanDuration, Long durationInNs, String duration, Long batchWeight, Long queuedBatches, Long maxQueuedBatches, Long numberOfActiveThreads, Long totalNumberOfThreads, SourceCounters sourceCounters, TargetCounters targetCounters) {
        super();
        this.processingState = processingState;
        this.inProgress = inProgress;
        this.neverRun = neverRun;
        this.initiatingUserId = initiatingUserId;
        this.sourceName = sourceName;
        this.sourceParameters = sourceParameters;
        this.targetPath = targetPath;
        this.stopping = stopping;
        this.scanning = scanning;
        this.paused = paused;
        this.succeeded = succeeded;
        this.failed = failed;
        this.stopped = stopped;
        this.inPlaceImportPossible = inPlaceImportPossible;
        this.dryRun = dryRun;
        this.startDate = startDate;
        this.scanEndDate = scanEndDate;
        this.endDate = endDate;
        this.scanDurationInNs = scanDurationInNs;
        this.scanDuration = scanDuration;
        this.durationInNs = durationInNs;
        this.duration = duration;
        this.batchWeight = batchWeight;
        this.queuedBatches = queuedBatches;
        this.maxQueuedBatches = maxQueuedBatches;
        this.numberOfActiveThreads = numberOfActiveThreads;
        this.totalNumberOfThreads = totalNumberOfThreads;
        this.sourceCounters = sourceCounters;
        this.targetCounters = targetCounters;
    }

    @JsonProperty("processingState")
    public String getProcessingState() {
        return processingState;
    }

    @JsonProperty("processingState")
    public void setProcessingState(String processingState) {
        this.processingState = processingState;
    }

    @JsonProperty("inProgress")
    public Boolean getInProgress() {
        return inProgress;
    }

    @JsonProperty("inProgress")
    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    @JsonProperty("neverRun")
    public Boolean getNeverRun() {
        return neverRun;
    }

    @JsonProperty("neverRun")
    public void setNeverRun(Boolean neverRun) {
        this.neverRun = neverRun;
    }

    @JsonProperty("initiatingUserId")
    public String getInitiatingUserId() {
        return initiatingUserId;
    }

    @JsonProperty("initiatingUserId")
    public void setInitiatingUserId(String initiatingUserId) {
        this.initiatingUserId = initiatingUserId;
    }

    @JsonProperty("sourceName")
    public String getSourceName() {
        return sourceName;
    }

    @JsonProperty("sourceName")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @JsonProperty("sourceParameters")
    public SourceParameters getSourceParameters() {
        return sourceParameters;
    }

    @JsonProperty("sourceParameters")
    public void setSourceParameters(SourceParameters sourceParameters) {
        this.sourceParameters = sourceParameters;
    }

    @JsonProperty("targetPath")
    public String getTargetPath() {
        return targetPath;
    }

    @JsonProperty("targetPath")
    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    @JsonProperty("stopping")
    public Boolean getStopping() {
        return stopping;
    }

    @JsonProperty("stopping")
    public void setStopping(Boolean stopping) {
        this.stopping = stopping;
    }

    @JsonProperty("scanning")
    public Boolean getScanning() {
        return scanning;
    }

    @JsonProperty("scanning")
    public void setScanning(Boolean scanning) {
        this.scanning = scanning;
    }

    @JsonProperty("paused")
    public Boolean getPaused() {
        return paused;
    }

    @JsonProperty("paused")
    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    @JsonProperty("succeeded")
    public Boolean getSucceeded() {
        return succeeded;
    }

    @JsonProperty("succeeded")
    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    @JsonProperty("failed")
    public Boolean getFailed() {
        return failed;
    }

    @JsonProperty("failed")
    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    @JsonProperty("stopped")
    public Boolean getStopped() {
        return stopped;
    }

    @JsonProperty("stopped")
    public void setStopped(Boolean stopped) {
        this.stopped = stopped;
    }

    @JsonProperty("inPlaceImportPossible")
    public Boolean getInPlaceImportPossible() {
        return inPlaceImportPossible;
    }

    @JsonProperty("inPlaceImportPossible")
    public void setInPlaceImportPossible(Boolean inPlaceImportPossible) {
        this.inPlaceImportPossible = inPlaceImportPossible;
    }

    @JsonProperty("dryRun")
    public Boolean getDryRun() {
        return dryRun;
    }

    @JsonProperty("dryRun")
    public void setDryRun(Boolean dryRun) {
        this.dryRun = dryRun;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("scanEndDate")
    public String getScanEndDate() {
        return scanEndDate;
    }

    @JsonProperty("scanEndDate")
    public void setScanEndDate(String scanEndDate) {
        this.scanEndDate = scanEndDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("scanDurationInNs")
    public Long getScanDurationInNs() {
        return scanDurationInNs;
    }

    @JsonProperty("scanDurationInNs")
    public void setScanDurationInNs(Long scanDurationInNs) {
        this.scanDurationInNs = scanDurationInNs;
    }

    @JsonProperty("scanDuration")
    public String getScanDuration() {
        return scanDuration;
    }

    @JsonProperty("scanDuration")
    public void setScanDuration(String scanDuration) {
        this.scanDuration = scanDuration;
    }

    @JsonProperty("durationInNs")
    public Long getDurationInNs() {
        return durationInNs;
    }

    @JsonProperty("durationInNs")
    public void setDurationInNs(Long durationInNs) {
        this.durationInNs = durationInNs;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @JsonProperty("batchWeight")
    public Long getBatchWeight() {
        return batchWeight;
    }

    @JsonProperty("batchWeight")
    public void setBatchWeight(Long batchWeight) {
        this.batchWeight = batchWeight;
    }

    @JsonProperty("queuedBatches")
    public Long getQueuedBatches() {
        return queuedBatches;
    }

    @JsonProperty("queuedBatches")
    public void setQueuedBatches(Long queuedBatches) {
        this.queuedBatches = queuedBatches;
    }

    @JsonProperty("maxQueuedBatches")
    public Long getMaxQueuedBatches() {
        return maxQueuedBatches;
    }

    @JsonProperty("maxQueuedBatches")
    public void setMaxQueuedBatches(Long maxQueuedBatches) {
        this.maxQueuedBatches = maxQueuedBatches;
    }

    @JsonProperty("numberOfActiveThreads")
    public Long getNumberOfActiveThreads() {
        return numberOfActiveThreads;
    }

    @JsonProperty("numberOfActiveThreads")
    public void setNumberOfActiveThreads(Long numberOfActiveThreads) {
        this.numberOfActiveThreads = numberOfActiveThreads;
    }

    @JsonProperty("totalNumberOfThreads")
    public Long getTotalNumberOfThreads() {
        return totalNumberOfThreads;
    }

    @JsonProperty("totalNumberOfThreads")
    public void setTotalNumberOfThreads(Long totalNumberOfThreads) {
        this.totalNumberOfThreads = totalNumberOfThreads;
    }

    @JsonProperty("sourceCounters")
    public SourceCounters getSourceCounters() {
        return sourceCounters;
    }

    @JsonProperty("sourceCounters")
    public void setSourceCounters(SourceCounters sourceCounters) {
        this.sourceCounters = sourceCounters;
    }

    @JsonProperty("targetCounters")
    public TargetCounters getTargetCounters() {
        return targetCounters;
    }

    @JsonProperty("targetCounters")
    public void setTargetCounters(TargetCounters targetCounters) {
        this.targetCounters = targetCounters;
    }



}
