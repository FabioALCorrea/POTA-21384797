
public class Cliente {

    String nome;
    String sexo;
    String endereco;
    String cidade;
    String email;
    String telefone;
    int idade;

    public Cliente(String nome, String sexo, String endereco, String cidade, String email, String telefone, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.cidade = cidade;
        this.email = email;
        this.telefone = telefone;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void prettyPrint() {
        System.out.println("Nome: " + nome);
        System.out.println("Sexo: " + sexo);
        System.out.println("Endereço: " + endereco);
        System.out.println("Cidade: " + cidade);
        System.out.println("E-mail: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("Idade: " + idade);
    }

}