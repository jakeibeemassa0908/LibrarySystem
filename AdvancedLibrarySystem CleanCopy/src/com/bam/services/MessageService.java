package com.bam.services;

import com.bam.dto.Messages;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bam.helper.*;

public class MessageService {
	private static  MessageService messageService = null;
	
	private MessageService(){}
	
	public static MessageService getInstance(){
		if (messageService == null){
			messageService = new MessageService();
		}
		return messageService;
	}
	DBConnection connection = DBConnection.getInstance();
	
	public void saveData(Map<String, String[]> map, int from, int to, String fromName, String toName) throws ParseException{
		Session session=null;
		try {
			session = connection.getSession();
			session.beginTransaction();
			Messages msg = new Messages();
			msg.setMessageContent(map.get("content")[0]);
			msg.setMessageDate(new Date());
			msg.setMessageFrom(from);
			msg.setMessageSubject(map.get("subject")[0]);
			msg.setMessageTo(to);
			msg.setMessageFromString(fromName);
			msg.setMessageToString(toName);
			msg.setOpenFlag(false);
			session.save(msg);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public List<Messages> getMessages(Integer from, Integer to,Integer id,Integer asker){
		List<Messages> messages=null;
		Session session1=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Criteria criteria =session1.createCriteria(Messages.class);
			if (from != null){
				criteria.add(Restrictions.like("messageFrom",from));
			}
			if (to != null){
				criteria.add(Restrictions.like("messageTo",to));
			}
			criteria.addOrder(Order.desc("messageDate"));
			if(id!=null){
				criteria.add(Restrictions.like("messageId",id));
			}
			if(asker!=null){
				if(asker==0){
					criteria.add(Restrictions.like("deletedByAdmin",false));
				}else{
					criteria.add(Restrictions.like("deletedByStudent",false));
				}
			}
			messages=criteria.list();
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
		session1.close();
		}
		return messages;
	}
	
	public void updateMessage(Integer id){
		Session session1=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Messages message= (Messages) session1.load(Messages.class, id);
			message.setOpenFlag(true);
			session1.update(message);
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
		session1.close();
		}
	}
	
	public long message_count(int receiver){
		Session session= null;
		long messages_number=0;
		try{
			session=connection.getSession();
			session.beginTransaction();
			messages_number= (long) session.createCriteria(Messages.class)
					.add(Restrictions.like("openFlag", false))
					.add(Restrictions.like("messageTo", receiver))
					.setProjection(Projections.count("messageId")).uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return messages_number;
	}
	
	public void setMessageDeleted(int messageId,int deleteBy){
		Session session1=null;
		try {
			session1 = connection.getSession();
			session1.beginTransaction();
			Messages message= (Messages) session1.load(Messages.class, messageId);
			if(deleteBy==0){
				message.setDeletedByAdmin(true);
			}else{
				message.setDeletedByStudent(true);
			}
			session1.update(message);
			session1.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
		session1.close();
		}
	}
}
