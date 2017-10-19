package com.hontek.synchronous.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hontek.comm.base.ApplicationContextUtil;
import com.hontek.review.dao.ProductDao;
import com.hontek.sys.pojo.TbInterAccount;


public class OnlyManager {
	
	static final private OnlyManager instance = new OnlyManager();
	
	private Map<String, TbInterAccount> accountMap = new HashMap<String, TbInterAccount>();
	
	private Map<String, Integer> proCodeSeq = new HashMap<String, Integer>();
	
	public static OnlyManager getInstance(){
		return instance;
	 }
	
	public OnlyManager() {
		TimeOut timeout = new TimeOut();
		timeout.start();
	}
	
	synchronized public TbInterAccount getAccount(String token){
		TbInterAccount interAccount = accountMap.get(token);
		return interAccount;
	}
	
	synchronized public void putAccount(String token,TbInterAccount interAcount){
		this.accountMap.put(token, interAcount);
	}
	
	synchronized public int getProCodeSeq(String entCode){
		Integer seq = proCodeSeq.get(entCode);
		if(seq==null){
			ProductDao productDao = (ProductDao) ApplicationContextUtil.getContext().getBean("productDao"); 
			seq = productDao.getDimentSeq(entCode);
		}else{
			seq = seq+1;
		}
		proCodeSeq.put(entCode, seq);
		return seq;
	}
	
	public class TimeOut extends Thread{
		public void run(){
			//����30����
			long keepTime = 1000L*60*60*10;
			
			while(true){
				try {
					Thread.sleep(1000*60*60*3);//����10����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Set<String> setKey = accountMap.keySet();
				Iterator<String> it = setKey.iterator();
				while(it.hasNext()){
					String key = it.next();
					TbInterAccount interAccount = accountMap.get(key);
					if(System.currentTimeMillis()-interAccount.getTime()>keepTime){
						accountMap.remove(key);
					}
				}
				
			}
		}
	}
	
	/**
	 * 心跳
	 * @param interaccount
	 */
	public void active(TbInterAccount interaccount){
		if(interaccount!=null){
			interaccount.setTime(System.currentTimeMillis());
		}
	}
}
