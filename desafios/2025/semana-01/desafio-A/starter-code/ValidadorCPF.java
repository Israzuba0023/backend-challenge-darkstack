public class ValidadorCPF {

    public static boolean validar(String cpf) {
        // 1. Limpeza: Remove todos os caracteres que não são dígitos
        String cpfLimpo = cpf.replaceAll("\\D", "");

        // --- O participante implementa a partir daqui ---

        // 2. Verificação de Tamanho
        // O CPF precisa ter exatamente 11 dígitos.
        if (cpfLimpo.length() != 11) {
            return false;
        }

        // 3. Verificação de Dígitos Repetidos
        // Se todos os dígitos forem iguais (ex: "11111111111"), não é válido.
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // 4. Cálculo do Primeiro Dígito Verificador
        // Fórmula: multiplicar cada um dos 9 primeiros dígitos por pesos de 10 a 2.
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto; // regra do CPF

        // 5. Cálculo do Segundo Dígito Verificador
        // Agora usamos os 10 primeiros dígitos (incluindo o digito1 calculado).
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        // 6. Validação Final
        // Comparar os dois dígitos calculados com os dois últimos do CPF informado.
        String digitosCalculados = "" + digito1 + digito2;
        String digitosInformados = cpfLimpo.substring(9, 11);

        return digitosCalculados.equals(digitosInformados);
    }

    // --- Área de Testes ---
    public static void main(String[] args) {
        System.out.println("--- Testando a Função validar ---");
        System.out.println("CPF \"123.456.789-09\" (válido): " + validar("123.456.789-09"));
        System.out.println("CPF \"111.111.111-11\" (inválido - repetido): " + validar("111.111.111-11"));
        System.out.println("CPF \"123.456.789-10\" (inválido - dígito incorreto): " + validar("123.456.789-10"));
        System.out.println("CPF \"12345678909\" (válido - sem formatação): " + validar("12345678909"));
        System.out.println("CPF \"98765432100\" (válido): " + validar("98765432100"));
    }
}
