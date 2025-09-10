package maven.demo;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		Scanner sc = new Scanner(System.in);
		
		
		//Sai da aplicacao caso conexao falhe
		
		
		dao.conectar();
		
		int opcao = -1;
		
		while(opcao != 5) {
	        System.out.println("\n=== MENU CARROS ===");
            System.out.println("1 - Listar carros");
            System.out.println("2 - Adicionar carro");
            System.out.println("3 - Modificar carro");
            System.out.println("4 - Excluir carro");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha
            
            switch(opcao) {
            	case 1:
            		Carro[] carros = dao.getCarross();
            		
            		if(carros != null) {
            			for(Carro c : carros) {
            				System.out.println(c.toString());
            			}
            		}
            		else {
            			 System.out.println("Nenhum carro encontrado.");
            		}
            		
            		break;
            		
            	case 2:
            		 System.out.print("Código: ");
                     int cod = sc.nextInt();
                     sc.nextLine();
                     System.out.print("Modelo: ");
                     String modelo = sc.nextLine();
                     System.out.print("Marca: ");
                     String marca = sc.nextLine();
                     System.out.print("Preço: ");
                     float preco = sc.nextFloat();
                     
                     Carro novo = new Carro(cod,modelo,marca,preco);
                     
                     if (dao.inserirCarro(novo)) {
                         System.out.println("✅ Carro inserido com sucesso!");
                     }
                     break;
            	case 3: // modificar
                    System.out.print("Informe o código do carro a modificar: ");
                    int codAlt = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo modelo: ");
                    String modeloAlt = sc.nextLine();
                    System.out.print("Nova marca: ");
                    String marcaAlt = sc.nextLine();
                    System.out.print("Novo preço: ");
                    float precoAlt = sc.nextFloat();

                    Carro carroAlt = new Carro(codAlt, modeloAlt, marcaAlt, precoAlt);
                    if (dao.atualizarCarro(carroAlt)) {
                        System.out.println("✅ Carro atualizado com sucesso!");
                    }
                    break;
            	case 4:
            		System.out.println("Digite o código do carro quer excluir: ");
            		int cod_exc = sc.nextInt();
            		sc.nextLine();
            		dao.excluirCarro(cod_exc);
            		break;
            	case 5:
            		System.out.println("Adeus!");
            		break;
            		
            	 default:
                     System.out.println("⚠️ Opção inválida!");
            
            }

		}
		sc.close(); // desaloca o scanner
		dao.close(); // Fecha a conexao com o banco
	}
		
}
