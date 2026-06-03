# Especificação de Regras de Negócio (RN)

Este documento estabelece as regras de validação e restrições de consistência lógica que devem ser aplicadas rigorosamente pela camada de serviço (`Service Layer`) do servidor Java.

| Identificador | Título da Regra | Descrição / Critério de Aceite |
| :--- | :--- | :--- |
| **RN01** | Valores Positivos Obrigatórios | Todo lançamento de receita bruta (`VariableIncome`), despesa diária (`VariableExpense`) ou custo fixo (`FixedExpense`) deve possuir o atributo `amount` estritamente maior que zero ($amount > 0$). |
| **RN02** | Inviolabilidade Temporal | A data de registro de ganhos por bico (`receivedAt`) e despesas diárias (`expenseDate`) deve ser menor ou igual à data atual do sistema. O sistema deve rejeitar lançamentos em datas futuras. |
| **RN03** | Validade de Prazo para Metas | A data limite de uma meta financeira de desejo (`targetDate`) deve ser estritamente maior do que a data em que a meta foi criada (`createdAt`). |
| **RN04** | Bloqueio de Desejos (Modo Sobrevivência) | Se o saldo líquido do usuário (Ganhos - Despesas Gerais) for menor que o valor total necessário para cobrir seus custos fixos do mês corrente, a entidade `FinancialGoal` deve ter seu estado (`currentState`) alterado para `PAUSED` automaticamente pelo sistema. |
| **RN05** | Alerta de Meta Humanamente Impossível | Caso o algoritmo do sistema calcule que a meta diária de trabalho necessária ultrapassa o atributo `dailyRevenueLimit` configurado no perfil do usuário, o sistema deve registrar a meta, mas emitir uma notificação de alerta de inviabilidade. |
| **RN06** | Consistência Territorial (Isolamento) | Toda e qualquer operação de leitura, cálculo, soma ou atualização realizada pela camada de dados do sistema deve conter obrigatoriamente a cláusula de filtragem pelo ID do usuário autenticado (`user_id`). |
| **RN07** | Exclusão em Cascata (Integridade) | Ao encerrar e deletar de forma definitiva a conta de um usuário (`User`), o sistema deve disparar uma remoção em cascata (*Cascade Delete*) de todos os seus registros nas tabelas secundárias. |
| **RN08** | Validação de Vencimento Recorrente | O atributo `dueDay` da entidade de custos fixos aceita apenas inteiros no intervalo fechado de **1 a 31**. Para meses com menos dias, o sistema deve computar o vencimento no último dia vigente do mês. |