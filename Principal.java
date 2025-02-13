import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        List<Funcionario> funcionarios = criarDataset();

        funcionarios.removeIf(f -> f.getNome().equals("João"));

        System.out.println("Lista de funcionários:");
        funcionarios.forEach(System.out::println);

        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários em suas funções:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(f -> System.out.println(" " + f));
        });

        System.out.println("\nAniversários de Outubro e Dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        Funcionario maisVelho = funcionarios.stream().max(Comparator.comparing(Funcionario::getIdade)).orElseThrow();

        System.out.println("\nFuncionário mais velho");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + maisVelho.getIdade());

        System.out.println("\n Ordem alfabética:");
        funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(System.out::println);

        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
                BigDecimal::add);
        System.out.println("\n Total (salários): " + formatarValor(totalSalarios));

        System.out.println("\n Salários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%s: %.2f salários mínimos%n", f.getNome(), salariosMinimos);
        });
    }
    
    private static List<Funcionario> criarDataset() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    private static String formatarValor(BigDecimal valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(valor);
    }
}
