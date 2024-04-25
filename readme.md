# Gestão da loja Balulak

## Projeto
Um software para gestão da loja Balulak

---
## Detalhes
O software fora planejado para extrair dados do excel e repassa-lo para o MySQL, permitindo alterações, updates, deletes e afins.

---
## Funcionalidades
- __radio button Mock__: Mock que preenche o banco de dados com dummys .
- __visualizar cliente__: podemos selecionar um cliente, ver seus pedidos, assim como dados do mesmo, como ID, Valor Total, Emissão, Fechamento, Status, assim como ao selecionar a row e pressionar enter visualizar todos os itens .
- __procurar cliente__: somos aptos a localizar um cliente a partir da pesquisa de seu nome, eliminando os outros da table. Pressione Voltar para eliminar os parametros da busca .
- __Cadastrar Cliente__: insira nome, CPF, cidade e escolha num comboBox qual o estado da cidade. O MySQL o dará automaticamente um ID .
- __Deletar Cliente__: selecione uma row e esse button irá o excluir do banco de dados .
- __Atualizar Cliente__: selecione uma row e esse button irá o abrir o painel para atualizar o cliente no banco de dados, abrindo um painel semelhante ao de cadastrar cliente, mas com os dados atuais preenchendo todos os campos .

---
## Como Utilizar

1. Instalar o Apache Netbeans no site oficial https://netbeans.apache.org/front/main/download/nb122/nb122/ .
2. Seguir as instruções de download que o site oficial indica .
3. Baixar o software compactado .
4. Caso não possua MySQL Workbench, baixe em https://dev.mysql.com/downloads/workbench/ .
5. É importante baixar o model.bak do projeto e no workbench selecione Database > Synchronize Model > Next > Next > selecione o Model Schema e Next > Next > Next > por bug do workbench, __APAGUE TODOS OS VISIBLE__ do código gerado e Execute > Close .
6. Descompactar e abrir a pasta NotasLoja no Netbeans .
7. Ter o JDK 19 .
8. Antes de mais nada, abra com.miguelefernando.DAO > BancoDAO.java e altere os dados no construtor para codizer com os do seu próprio Servidor MySQL .
9. Clicar com o botão direito do mouse em cima do projeto ou F6 .
10. Selecionar "Run File" .
11. Devido ao fato de ainda não ser possível trabalhar com dados previamente existentes, há um discreto radiobutton no canto inferior esquerdo da tela de inicial, você deve pressiona-lo para preencher o banco com um mock .
12. pronto para uso .

---
## Futuras Atualizações
- Método para fazer unmashling dos dados a partir do Excel não normalizado onde os registros da loja balulak ficam salvos, normaliza-los e sincronizar automaticamente o mysql .
