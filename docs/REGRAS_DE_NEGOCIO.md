# Regras de Negócio (RN)

Este documento descreve as restrições e regras de validação que serão implementadas na camada de serviço (`Service Layer`) do sistema Java.

| Identificador | Título da Regra | Descrição / Critério de Aceite |
| :--- | :--- | :--- |
| **RN01** | Valores Positivos Obrigatórios | Todo lançamento de receita ou custo deve possuir o valor (`amount`) maior que zero. |
| **RN04** | Bloqueio de Desejos (Modo Sobrevivência) | Se o saldo do usuário for menor que os custos fixos do mês, as metas de desejo são pausadas (`PAUSED`) automaticamente. |
| **RN05** | Alerta de Meta Impossível | Se o cálculo diário ultrapassar o `dailyRevenueLimit` configurado pelo usuário, o sistema emite um alerta de inviabilidade. |