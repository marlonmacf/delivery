package com.demo.authserver.domain.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.demo.authserver.infrastructure.entities.MongoApproval;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;

public class CustomApprovalStore implements ApprovalStore {

	@Autowired
	private MongoTemplate mongoTemplate;

	private boolean handleRevocationsAsExpiry = false;

	@Override
	public boolean addApprovals(Collection<Approval> approvals) {
		boolean isSuccess = true;
		Iterator<Approval> iterator = approvals.iterator();
		while (iterator.hasNext()) {
			Approval approval = iterator.next();
			if (!updateApproval(approval) && !addApproval(approval)) {
				isSuccess = false;
			}
		}
		return isSuccess;
	}

	private boolean updateApproval(Approval approval) {
		Query query = new Query();
		query.addCriteria(Criteria.where(MongoApproval.USER_ID).is(approval.getUserId()));
		query.addCriteria(Criteria.where(MongoApproval.CLIENT_ID).is(approval.getClientId()));
		query.addCriteria(Criteria.where(MongoApproval.SCOPE).is(approval.getScope()));

		Update update = new Update();
		update.set(MongoApproval.EXPIRE_AT, approval.getExpiresAt().getTime());
		update.set(MongoApproval.STATUS,
				approval.getStatus() != null ? approval.getStatus() : Approval.ApprovalStatus.APPROVED);
		update.set(MongoApproval.LAST_MODIFIED_AT, approval.getLastUpdatedAt().getTime());

		UpdateResult updateResult = mongoTemplate.updateFirst(query, update, MongoApproval.class);
		return (updateResult.getModifiedCount() > 0);
	}

	private boolean addApproval(Approval approval) {
		MongoApproval mongoApproval = new MongoApproval();
		mongoApproval.setUserId(approval.getUserId());
		mongoApproval.setClientId(approval.getClientId());
		mongoApproval.setScope(approval.getScope());
		mongoApproval.setExpireAt(approval.getExpiresAt().getTime());
		mongoApproval.setLastModifiedAt(approval.getLastUpdatedAt().getTime());
		mongoApproval.setStatus(approval.getStatus() != null ? approval.getStatus() : Approval.ApprovalStatus.APPROVED);
		try {
			mongoTemplate.save(mongoApproval);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean revokeApprovals(Collection<Approval> approvals) {
		boolean isSuccess = true;
		Iterator<Approval> iterator = approvals.iterator();

		while (iterator.hasNext()) {
			Approval approval = iterator.next();
			if (handleRevocationsAsExpiry) {
				Query query = new Query();
				query.addCriteria(Criteria.where(MongoApproval.USER_ID).is(approval.getUserId()));
				query.addCriteria(Criteria.where(MongoApproval.CLIENT_ID).is(approval.getClientId()));
				query.addCriteria(Criteria.where(MongoApproval.SCOPE).is(approval.getScope()));

				Update update = new Update();
				update.set(MongoApproval.EXPIRE_AT, System.currentTimeMillis());

				UpdateResult updateResult = mongoTemplate.updateFirst(query, update, MongoApproval.class);
				isSuccess = (updateResult.getModifiedCount() == 1);
			} else {
				Query query = new Query();
				query.addCriteria(Criteria.where(MongoApproval.USER_ID).is(approval.getUserId()));
				query.addCriteria(Criteria.where(MongoApproval.CLIENT_ID).is(approval.getClientId()));
				query.addCriteria(Criteria.where(MongoApproval.SCOPE).is(approval.getScope()));

				DeleteResult deleteResult = mongoTemplate.remove(query, MongoApproval.class);
				isSuccess = (deleteResult.getDeletedCount() == 1);
			}
		}
		return isSuccess;
	}

	@Override
	public Collection<Approval> getApprovals(String username, String clientId) {
		Collection<Approval> approvals = new ArrayList<>();

		Query query = new Query();
		query.addCriteria(Criteria.where(MongoApproval.CLIENT_ID).is(clientId));
		query.addCriteria(Criteria.where(MongoApproval.USER_ID).is(username));

		List<MongoApproval> mongoApprovals = mongoTemplate.find(query, MongoApproval.class);

		for (MongoApproval mongoApproval : mongoApprovals) {
			approvals.add(new Approval(mongoApproval.getUserId(), mongoApproval.getClientId(), mongoApproval.getScope(),
					new Date(mongoApproval.getExpireAt()), mongoApproval.getStatus(),
					new Date(mongoApproval.getLastModifiedAt())));
		}

		return approvals;
	}

	public boolean isHandleRevocationsAsExpiry() {
		return handleRevocationsAsExpiry;
	}

	public void setHandleRevocationsAsExpiry(boolean handleRevocationsAsExpiry) {
		this.handleRevocationsAsExpiry = handleRevocationsAsExpiry;
	}
}