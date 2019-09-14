package top.docstorm.documentstormcommon.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * file_info
 * @author 
 */
public class FileInfo implements Serializable {
    /**
     * 文件id
     */
    private Integer fileId;

    /**
     * 文件唯一key，用于在硬盘中的文件名
     */
    private String fileKey;

    /**
     * 原文件名
     */
    private String fileName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近更新时间
     */
    private Date updateLastTime;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 0为转换中，1为转换完成，2为过期文件
     */
    private Byte fileStatus;

    /**
     * 文件格式转换的类型，0为word2pdf，1为pdf2word，2为markdown2pdf，3为markdown2html，4为word translate，5为html2pdf
     */
    private Byte fileFormatChangeType;

    private static final long serialVersionUID = 1L;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateLastTime() {
        return updateLastTime;
    }

    public void setUpdateLastTime(Date updateLastTime) {
        this.updateLastTime = updateLastTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Byte fileStatus) {
        this.fileStatus = fileStatus;
    }

    public Byte getFileFormatChangeType() {
        return fileFormatChangeType;
    }

    public void setFileFormatChangeType(Byte fileFormatChangeType) {
        this.fileFormatChangeType = fileFormatChangeType;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FileInfo other = (FileInfo) that;
        return (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getFileKey() == null ? other.getFileKey() == null : this.getFileKey().equals(other.getFileKey()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateLastTime() == null ? other.getUpdateLastTime() == null : this.getUpdateLastTime().equals(other.getUpdateLastTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getFileStatus() == null ? other.getFileStatus() == null : this.getFileStatus().equals(other.getFileStatus()))
            && (this.getFileFormatChangeType() == null ? other.getFileFormatChangeType() == null : this.getFileFormatChangeType().equals(other.getFileFormatChangeType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getFileKey() == null) ? 0 : getFileKey().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateLastTime() == null) ? 0 : getUpdateLastTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getFileStatus() == null) ? 0 : getFileStatus().hashCode());
        result = prime * result + ((getFileFormatChangeType() == null) ? 0 : getFileFormatChangeType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileKey=").append(fileKey);
        sb.append(", fileName=").append(fileName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateLastTime=").append(updateLastTime);
        sb.append(", userId=").append(userId);
        sb.append(", fileStatus=").append(fileStatus);
        sb.append(", fileFormatChangeType=").append(fileFormatChangeType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}