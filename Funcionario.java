import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }
    
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public int getIdade() {
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", 
                getNome(),
                getDataNascimentoFormatada(),
                String.format("%,.2f", salario),
                funcao);
    }
}