package engine;

public class Feedback {

    private Boolean success;
    private String feedback;

    public Feedback() {

    }

    public Feedback(Boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
