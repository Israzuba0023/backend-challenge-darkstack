function validarCPF(cpf) {
  // 1. Limpeza: Remove todos os caracteres que não são dígitos
  const cpfLimpo = cpf.replace(/\D/g, '');

  // 2. Verificação de Tamanho
  if (cpfLimpo.length !== 11) {
    return false; // CPF precisa ter exatamente 11 dígitos
  }

  // 3. Verificação de Dígitos Repetidos
  // Exemplo: "11111111111", "00000000000", etc.
  if (/^(\d)\1{10}$/.test(cpfLimpo)) {
    return false;
  }

  // 4. Cálculo do Primeiro Dígito Verificador
  // Fórmula: somar os 9 primeiros dígitos multiplicados por pesos decrescentes de 10 a 2
  let soma = 0;
  for (let i = 0; i < 9; i++) {
    soma += parseInt(cpfLimpo[i]) * (10 - i);
  }
  let resto = (soma * 10) % 11;
  if (resto === 10) resto = 0; // regra: se resto for 10, usar 0
  const primeiroDigito = resto;

  // 5. Cálculo do Segundo Dígito Verificador
  // Fórmula: somar os 10 primeiros dígitos (inclui o primeiro DV) multiplicados por pesos de 11 a 2
  soma = 0;
  for (let i = 0; i < 10; i++) {
    soma += parseInt(cpfLimpo[i]) * (11 - i);
  }
  resto = (soma * 10) % 11;
  if (resto === 10) resto = 0;
  const segundoDigito = resto;

  // 6. Validação Final
  // Verifica se os dois últimos dígitos do CPF são iguais aos calculados
  return (
    primeiroDigito === parseInt(cpfLimpo[9]) &&
    segundoDigito === parseInt(cpfLimpo[10])
  );
}

// --- Área de Testes ---
console.log('--- Testando a Função validarCPF ---');
console.log('CPF "123.456.789-09" (válido):', validarCPF("123.456.789-09"));
console.log('CPF "111.111.111-11" (inválido - repetido):', validarCPF("111.111.111-11"));
console.log('CPF "123.456.789-10" (inválido - dígito incorreto):', validarCPF("123.456.789-10"));
console.log('CPF "12345678909" (válido - sem formatação):', validarCPF("12345678909"));
console.log('CPF "98765432100" (válido):', validarCPF("98765432100"));
