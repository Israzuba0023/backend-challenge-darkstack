import re

def validar_cpf(cpf: str) -> bool:
    # 1. Limpeza: Remove todos os caracteres que não são dígitos
    cpf_limpo = re.sub(r'\D', '', cpf)

    # 2. Verificação de Tamanho
    if len(cpf_limpo) != 11:
        return False

    # 3. Verificação de Dígitos Repetidos
    if cpf_limpo == cpf_limpo[0] * 11:
        return False

    # 4. Cálculo do Primeiro Dígito Verificador
    soma = 0
    for i, num in enumerate(cpf_limpo[:9]):
        soma += int(num) * (10 - i)
    resto = soma % 11
    digito1 = 0 if resto < 2 else 11 - resto

    # 5. Cálculo do Segundo Dígito Verificador
    soma = 0
    for i, num in enumerate(cpf_limpo[:9] + str(digito1)):
        soma += int(num) * (11 - i)
    resto = soma % 11
    digito2 = 0 if resto < 2 else 11 - resto

    # 6. Validação Final
    return cpf_limpo[-2:] == f"{digito1}{digito2}"

# --- Área de Testes ---
if __name__ == "__main__":
    print('--- Testando a Função validar_cpf ---')
    print('CPF "123.456.789-09" (válido):', validar_cpf("123.456.789-09"))
    print('CPF "111.111.111-11" (inválido - repetido):', validar_cpf("111.111.111-11"))
    print('CPF "123.456.789-10" (inválido - dígito incorreto):', validar_cpf("123.456.789-10"))
    print('CPF "12345678909" (válido - sem formatação):', validar_cpf("12345678909"))
    print('CPF "98765432100" (válido):', validar_cpf("98765432100"))
