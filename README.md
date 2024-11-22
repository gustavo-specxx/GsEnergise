Projeto: Gestão de Consumo Energético
Este projeto é uma aplicação web desenvolvida em Spring Boot com Thymeleaf, que permite gerenciar produtos e usuários, focando no consumo energético. Além disso, inclui insights gerados pelo ChatGPT para economia de energia.

Sumário
Descrição do Projeto
Funcionalidades
Configurações Iniciais
Como Executar o Projeto
Pré-requisitos
Passos para Execução
Estrutura do Projeto
Classes Principais
Classe Produto
Classe Usuario
Classe ConsumoEnergetico
Templates Thymeleaf
Insights para Economia de Energia
Tecnologias Utilizadas
Contribuições
Licença
Descrição do Projeto
O objetivo deste projeto é criar uma aplicação web que permita:

Cadastrar e listar produtos com informações sobre consumo energético.
Gerenciar usuários com autenticação e validação.
Analisar o consumo energético dos produtos cadastrados.
Fornecer insights para economia de energia, utilizando sugestões geradas pelo ChatGPT.
Funcionalidades
Cadastro de Produtos:

Nome do produto.
Consumo energético (em kWh).
Gerenciamento de Usuários:

Cadastro de novos usuários.
Validação de dados de entrada.
Análise de Consumo Energético:

Cálculo do consumo total dos produtos.
Identificação de produtos com alto consumo.
Insights para Economia de Energia:

Dicas e sugestões para reduzir o consumo energético.
Recomendações personalizadas baseadas nos produtos cadastrados.
Configurações Iniciais
O projeto foi configurado utilizando o Spring Initializr com as seguintes dependências:

Spring Boot DevTools
Spring Web
Thymeleaf
Spring Data JPA
MySQL Driver
Validation
Como Executar o Projeto
Pré-requisitos
Java 17 ou superior.
Maven 3.6 ou superior.
MySQL ou outro banco de dados compatível.
IDE de sua preferência (IntelliJ IDEA, Eclipse, etc.).
Passos para Execução
Clonar o Repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/seu-repositorio.git
Importar o Projeto na IDE:

Importar como um projeto Maven existente.
Configurar o Banco de Dados:

Criar um banco de dados no MySQL (por exemplo, gestao_energia).

Atualizar o arquivo src/main/resources/application.properties com as credenciais do seu banco de dados:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_energia
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
Executar o Projeto:

Pela linha de comando:

bash
Copiar código
mvn spring-boot:run
Ou pela IDE, executando a classe DemoApplication.

Acessar a Aplicação:

Abra o navegador e acesse http://localhost:8081/.
Estrutura do Projeto
Classes Principais
Classe Produto
Representa os produtos com informações de consumo energético.

java
Copiar código
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "O consumo energético é obrigatório")
    @Column(nullable = false)
    private Double consumoEnergetico;

    // Getters e Setters
}
Classe Usuario
Gerencia os usuários da aplicação.

java
Copiar código
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Column(nullable = false)
    private String senha;

    // Getters e Setters
}
Classe ConsumoEnergetico
Realiza cálculos e análises sobre o consumo energético.

java
Copiar código
public class ConsumoEnergetico {

    public Double calcularConsumoTotal(List<Produto> produtos) {
        return produtos.stream()
                .mapToDouble(Produto::getConsumoEnergetico)
                .sum();
    }

    public List<Produto> identificarProdutosAltaConsumo(List<Produto> produtos, Double limite) {
        return produtos.stream()
                .filter(p -> p.getConsumoEnergetico() > limite)
                .collect(Collectors.toList());
    }

    // Outros métodos relacionados
}
Templates Thymeleaf
Os templates estão localizados em src/main/resources/templates.

produtos-listar.html: Lista de produtos.
produtos-adicionar.html: Formulário para adicionar produtos.
usuario-form.html: Formulário para cadastro de usuários.
usuarios-lista.html: Lista de usuários.
Insights para Economia de Energia
Com base nos produtos cadastrados e seus consumos energéticos, a aplicação fornece insights gerados pelo ChatGPT para ajudar na economia de energia. Algumas das dicas incluem:

Desligar dispositivos em stand-by: Muitos aparelhos continuam consumindo energia mesmo quando não estão em uso.
Utilizar lâmpadas LED: Elas consomem menos energia e duram mais que as convencionais.
Manutenção regular: Equipamentos em bom estado funcionam de forma mais eficiente.
Uso consciente de ar-condicionado: Ajustar a temperatura e realizar manutenções periódicas.
Isolamento térmico: Melhora a eficiência do aquecimento e resfriamento de ambientes.
Tecnologias Utilizadas
Java 17
Spring Boot 3
Thymeleaf
Spring Data JPA
Hibernate
MySQL
Bootstrap 5
Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
