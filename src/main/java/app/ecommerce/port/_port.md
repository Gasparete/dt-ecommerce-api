# port

As "portas" de comunicação do seu core. Este pacote contém as interfaces que definem como o seu domínio (domain e
application) se comunica com a camada de infraestrutura. Ele permite que sua lógica de negócio dependa de contratos (
CpfValidationPort), e não de implementações concretas (FederalCpfSystemAdapter).