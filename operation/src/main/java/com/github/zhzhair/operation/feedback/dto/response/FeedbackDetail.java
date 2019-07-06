package com.github.zhzhair.operation.feedback.dto.response;

import com.github.zhzhair.operation.feedback.domain.FeedbackEntity;
import com.github.zhzhair.operation.feedback.domain.FeedbackReplyEntity;

import java.util.List;

public class FeedbackDetail {
    private FeedbackEntity feedbackEntity;
    private List<FeedbackReplyEntity> feedbackReplyEntities;

    public FeedbackEntity getFeedbackEntity() {
        return feedbackEntity;
    }

    public void setFeedbackEntity(FeedbackEntity feedbackEntity) {
        this.feedbackEntity = feedbackEntity;
    }

    public List<FeedbackReplyEntity> getFeedbackReplyEntities() {
        return feedbackReplyEntities;
    }

    public void setFeedbackReplyEntities(List<FeedbackReplyEntity> feedbackReplyEntities) {
        this.feedbackReplyEntities = feedbackReplyEntities;
    }
}
