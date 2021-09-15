package view;

import java.util.concurrent.Semaphore;

import controller.CompraController;

public class Principal {


	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		
		for (int idClientes = 1; idClientes <= 300; idClientes++) {
			CompraController compraController = new CompraController(idClientes, semaphore);
			compraController.start();
			
		}
		

	}

}
