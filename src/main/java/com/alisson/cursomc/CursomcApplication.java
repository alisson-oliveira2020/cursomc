package com.alisson.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alisson.cursomc.domain.Categoria;
import com.alisson.cursomc.domain.Cidade;
import com.alisson.cursomc.domain.Cliente;
import com.alisson.cursomc.domain.Endereco;
import com.alisson.cursomc.domain.Estado;
import com.alisson.cursomc.domain.Pagamento;
import com.alisson.cursomc.domain.PagamentoComBoleto;
import com.alisson.cursomc.domain.PagamentoComCartao;
import com.alisson.cursomc.domain.Pedido;
import com.alisson.cursomc.domain.Produto;
import com.alisson.cursomc.domain.enums.EstadoPagamento;
import com.alisson.cursomc.domain.enums.TipoCliente;
import com.alisson.cursomc.repositories.CategoriaRepository;
import com.alisson.cursomc.repositories.CidadeRepository;
import com.alisson.cursomc.repositories.ClienteRepository;
import com.alisson.cursomc.repositories.EnderecoRepository;
import com.alisson.cursomc.repositories.EstadoRepository;
import com.alisson.cursomc.repositories.PagamentoRepository;
import com.alisson.cursomc.repositories.PedidoRepository;
import com.alisson.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "91849831", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2372872", "3823921"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "123", "Apt. 21", "Sta. Etelvina", "080038329", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos Flores", "123", "Apt. 21", "Sandro Etelvina", "28003821", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/08/2017 13:12"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("21/08/2017 13:12"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		
		
		
	}

}
