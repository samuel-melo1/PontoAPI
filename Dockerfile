## Use a imagem base com JDK e Maven instalados
#FROM maven:alpine
#
## Defina o diretório de trabalho dentro do contêiner
#WORKDIR /app
#
## Copie o arquivo pom.xml separadamente para aproveitar o cache de dependências
#COPY pom.xml .
#
## Baixe as dependências do Maven
#RUN mvn dependency:go-offline
#
## Copie o restante do código-fonte
#COPY src ./src
#
## Compile o código-fonte e empacote o aplicativo em um arquivo JAR
#RUN mvn package
#
## Defina o comando padrão a ser executado quando o contêiner for iniciado
#CMD ["java", "-jar", "target/PontoAPI-0.0.1-SNAPSHOT.jar"]
