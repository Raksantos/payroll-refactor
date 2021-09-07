# Projeto OO: Sistema de Folha de Pagamento

<p align="justify"> O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e impostos deduzidos do salário.
</p>

* <p align="justify">Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal durante as horas extras. Eles são pagos toda sexta-feira.</p>

* <p align="justify">Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês (desconsidere feriados). Tais empregados são chamados "assalariados".</p>

* <p align="justify">Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um percentual das vendas que realizam. Eles submetem resultados de vendas que informam a data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas de salário fixo mais as comissões do período.
  
  - Empregados podem escolher o método de pagamento.
  - Podem receber um cheque pelos correios
  - Podem receber um cheque em mãos
  - Podem pedir depósito em conta bancária

</p>

* <p align="justify">Alguns empregados pertencem ao sindicato (para simplificar, só há um possível sindicato). O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo contracheque do empregado. A identificação do empregado no sindicato não é a mesma da identificação no sistema de folha de pagamento.</p>

* <p align="justify">A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o pagamento para cada empregado desde a última vez em que este foi pago.</p>

## Funções 
|  Título        | Descrição | Progresso atual | Finalizado | 
|----------------|---------------|----------------|-----------|
| Adição de um empregado  | Um novo empregado é adicionado ao sistema. Os seguintes atributos são fornecidos: nome, endereço, tipo (hourly, salaried, commissioned) e os atributos associados (salário horário, salário mensal, comissão). Um número de empregado (único) deve ser escolhido automaticamente pelo sistema. | Concluído | <ul><li>[x] ok</li></ul>
| Remoção de um empregado | Um empregado é removido do sistema. | Concluído | <ul><li>[x] ok</li></ul>
| Listagem de empregados (EXTRA) | Para cada empregado registrado no sistema da empresa, são listados os seguintes dados: nome, endereço, tipo, método de pagamento, dados do sindicato, taxas, cartões de ponto e resultados de vendas (de acordo com o tipo de empregado) | Concluído | <ul><li>[x] ok</li></ul>
| Lançar um Cartão de Ponto | O sistema anotará a informação do cartão de ponto e a associará ao empregado correto. | Concluído | <ul><li>[x] ok</li></ul>
| Lançar um Resultado Venda | O sistema anotará a informação do resultado da venda e a associará ao empregado correto. | Concluído | <ul><li>[x] ok</li></ul>
| Lançar uma taxa de serviço | O sistema anotará a informação da taxa de serviço e a associará ao empregado correto. | Concluído | <ul><li>[x] ok</li></ul>
| Alterar detalhes de um empregado | Os seguintes atributos de um empregado podem ser alterados: nome, endereço, tipo (hourly,salaried,commisioned), método de pagamento, se pertence ao sindicato ou não, identificação no sindicato, taxa sindical. | Concluído | <ul><li>[x] ok</li></ul>
| Rodar a folha de pagamento para hoje | O sistema deve achar todos os empregados que devem ser pagos no dia indicado, deve calcular o valor do salário e deve providenciar o pagamento de acordo com o método escolhido pelo empregado. | Concluído | <ul><li>[x] ok</li></ul>
| Undo/redo | Qualquer transação associada as funcionalidades 1 a 7 deve ser desfeita (undo) ou refeita (redo). | Concluído | <ul><li>[x] ok</li></ul>
| Agenda de Pagamento | Cada empregado é pago de acordo com uma "agenda de pagamento". Empregados podem selecionar a agenda de pagamento que desejam. Por default, as agendas "semanalmente", "mensalmente" e "bi-semanalmente" são usadas, como explicado na descricao  geral. Posteriormente, um empregado pode pedir para ser pago de acordo com qualquer uma dessas agendas. | Concluído | <ul><li>[x] ok</li></ul>
| Criação de Novas Agendas de Pagamento | A direção da empresa pode criar uma nova agenda de pagamento e disponibilizá-la para os empregados escolherem, se assim desejarem. Uma agenda é especificada através de um string. Alguns exemplos mostram as possibilidades: "mensal 1": dia 1 de todo mês "mensal 7": dia 7 de todo mês "mensal $": último dia útil de todo mês "semanal 1 segunda": toda semana às segundas-feiras "semanal 1 sexta": toda semana às sextas-feiras "semanal 2 segunda": a cada 2 semanas às segundas-feiras | Concluído | <ul><li>[x] ok</li></ul>

## Code Smells

### Long Method

- Métodos extensos que acumulam muitas variáveis locais; | Corrigido |

- Métodos que acumulam decisões lógicas (toStrings); | Corrigido - [Link](https://github.com/Raksantos/payroll-refactor/commit/504e40f021569fc06b3a857b2ee0d947ad871db6) |

- Método launch payroll com muitas responsabilidades de fazer o pagamento de acordo com o tipo de usuário | Corrigido - [Link](https://github.com/Raksantos/payroll-refactor/commit/032f5136cb6b7e0c6b6a33a01521b71003be2a45) |

### Generative Speculation

- Construtores vazios de várias classes não são utilizados; | Corrigido - [Link](https://github.com/Raksantos/payroll-refactor/commit/60a7ae71a9604d2545e90ae4b06c909b3bb9f1bf)|

- Métodos getters e setters de várias classes nunca são utilizados; | Corrigido - [Link](https://github.com/Raksantos/payroll-refactor/commit/60a7ae71a9604d2545e90ae4b06c909b3bb9f1bf)|

### Data Class

- Quantidade numerosa de métodos na classe EmployeeController; | Corrigido durante a própria AB1 com as classes utils|

### Feature Envy

- O método VerifyPayDate() está mais relacionado à classe PaymentData, que lida com os dados contidos na classe PayCheck. | Corrigido - [Link](https://github.com/Raksantos/payroll-refactor/commit/84a74536642c40f87f63a3037e9b9b1afd9562f8) |

## Refactoring

### Strategy

- Foi aplicado o Design Pattern Strategy para solucionar um code smell da classe PaymentsControl, no método VerifyPayDate(), definindo uma interface com os métodos abstratos criando uma classe concreta para cada tipo de agenda, com as implementações do comportamento adequado para cada uma delas. Na classe PaymentSchedule, foi adicionado um atributo strategy, além disso foi adicionado um novo método nessa interface, de forma a modularizar o código (método getDateInSchedule) | Feito - [Link](https://github.com/Raksantos/payroll-refactor/commit/e869953224a5217a11b7ed6f3cdf40bb9b619cbd) |

### Memento

- Refatoração das funcionalidades de Undo/Redo para uma classe específica, utilizando a estratégia de histórico do objeto Company para realizar as operações de salvar, undo e redo; | Feito - [Link](https://github.com/Raksantos/payroll-refactor/commit/9f88de9ba48171d0df4d7be161606091d237803e) |

### Remove Generative Speculation

- Alguns métodos construtores, getters e setters nunca foram utilizados. Logo, foram removidos na refatoração. | Feito - [Link](https://github.com/Raksantos/payroll-refactor/commit/60a7ae71a9604d2545e90ae4b06c909b3bb9f1bf) |

### Move Accumulation to Collecting Parameter

- Simplificação do toString das classes Employee e PaymentList | Feito - [Link](https://github.com/Raksantos/payroll-refactor/commit/504e40f021569fc06b3a857b2ee0d947ad871db6) |

### Move method

- O método verifyPayDate() foi movido para a classe PaymentData, pois lida com os paychecks (comprovantes de pagamento) e as agendas | Feito - [Link](https://github.com/Raksantos/payroll-refactor/commit/84a74536642c40f87f63a3037e9b9b1afd9562f8) |