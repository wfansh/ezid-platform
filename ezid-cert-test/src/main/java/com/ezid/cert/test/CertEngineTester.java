package com.ezid.cert.test;

import java.util.ArrayList;
import java.util.List;

import com.ezid.cert.task.AbstractTaskExecuter;


public class CertEngineTester {
	
	public static final String URL = "http://localhost:8080/activiti-rest/service/";
	public static final String USER_NAME = "admin";
	public static final String PASSWORD = "admin";
	
	private List<MockSocialSecCertCreator> certCreaters = new ArrayList<MockSocialSecCertCreator>();
	private List<MockQuitz> taskExecutors = new ArrayList<MockQuitz>();
	
	
	
	public static void main(String[] args) {
		CertEngineTester tester = new CertEngineTester();
		tester.run();
	}

	public void run() {
//		for (int i = 0; i<10; i++) {
//			MockSocialSecCertCreator certCreater = new MockSocialSecCertCreator();
//			certCreaters.add(certCreater);
//			certCreater.start();
//		}
		long startTime = System.currentTimeMillis();
		MockSocialSecCertCreator certCreater = new MockSocialSecCertCreator();
		certCreater.start();
		try {
			certCreater.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
		createMockMachine(new MockPhotoPreProcessor("MockPhotoPreProcessor1", "machine_user0", "machine_user0"));
		createMockMachine(new MockPhotoPreProcessor("MockPhotoPreProcessor2", "machine_user1", "machine_user1"));
		createMockMachine(new MockPhotoAdapter("MockPhotoAdapter1", "machine_user2", "machine_user2"));
		createMockMachine(new MockPhotoAdapter("MockPhotoAdapter2", "machine_user3", "machine_user3"));
		createMockMachine(new MockMachineCertificationAdapter("MockMachineCertificationAdapter1", "machine_user4", "machine_user4"));
		createMockMachine(new MockMachineCertificationAdapter("MockMachineCertificationAdapter2", "machine_user5", "machine_user5"));
		createMockMachine(new MockManualCertificationAdapter("MockManualCertificationAdapter1", "user1", "user1"));
		createMockMachine(new MockManualCertificationAdapter("MockManualCertificationAdapter2", "user2", "user2"));
		
		
//		for (MockSocialSecCertCreator item : certCreaters) {
//			try {
//				item.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		for (MockQuitz item: taskExecutors) {
			try {
				item.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("total " + ((System.currentTimeMillis() - startTime)/1000/60));
	}
	
	private void createMockMachine( AbstractTaskExecuter executor) {
		MockQuitz machineUser = new MockQuitz(executor);
		taskExecutors.add(machineUser);
		machineUser.start();
	}
	
	class MockQuitz extends Thread {
		private AbstractTaskExecuter taskExecuter;
		public MockQuitz(AbstractTaskExecuter taskExecuter) {
			this.taskExecuter = taskExecuter;
		}
		public void run() {
			while (true) {
				taskExecuter.execute();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	

}
