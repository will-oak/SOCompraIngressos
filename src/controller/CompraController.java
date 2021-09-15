package controller;

import java.util.concurrent.Semaphore;


public class CompraController extends Thread {
	
	
	private volatile boolean fim = false;
	private Semaphore semaphore;
	private int idClient;
	private static int numberVoucher = 100;

	public CompraController(int idClient, Semaphore semaphore) {
		this.idClient = idClient;
		this.semaphore = semaphore;
	}
	
	@Override
	public void run() {
		login();
		while(!fim) {
			
		}
		
		}
			
	
	private void login() {
		int timeLogin = (int) ((Math.random() * 2000) + 50);
		
		try {
			sleep(timeLogin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (timeLogin > 1000) {
			System.err.println("Usuário #" + idClient + " teve problemas no login: Time Out");
			
		} else {
			voucherChoose();
		}
		
	}
	
	private void voucherChoose() {
		int timeChoose = (int) ((Math.random() * 3000) + 1000);
		
		try {
			sleep(timeChoose);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (timeChoose > 2500) {
			System.err.println("Usuário #" + idClient + " teve problemas com a compra: Session Expired");
			
		} else {
			try {
				semaphore.acquire();
				
				int numberVoucherBuy = (int) ((Math.random() * 4) + 1);
				
				
				if (numberVoucherBuy > numberVoucher) {
					System.err.println("Caro usuário #" + idClient + ", a quantidade de ingressos solicitadas (" + numberVoucherBuy + ") não estão disponíveis para compra.");
				} else {
					buyIngressos(numberVoucherBuy);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	private void buyIngressos(int numberVoucherBuy) {
		numberVoucher -= numberVoucherBuy;
		
		System.out.println("Usuário #" + idClient + " comprou " + numberVoucherBuy + ". [" + numberVoucher + " ingressos disponíveis].");
		semaphore.release();
		
		if (numberVoucher == 0){
			 fim = true;
		}
		
		
		
	}

}
