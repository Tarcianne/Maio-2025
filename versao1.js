const sabores = {
    maracuja: { estoque: 40, preco: 3.00 },
    morango: { estoque: 30, preco: 3.50 },
    goiaba: { estoque: 25, preco: 2.50 },
    chocolate: { estoque: 50, preco: 4.00 },
    coco: { estoque: 35, preco: 3.00 },
    limao: { estoque: 20, preco: 2.00 }
  };
  
  let compraFinalizada = false;
  
  document.getElementById('calcular').addEventListener('click', () => {
    if (compraFinalizada) {
      alert("Você já finalizou a compra. Recarregue a página para continuar.");
      return;
    }
  
    let total = 0;
    let erroEstoque = false;
  
    for (let sabor in sabores) {
      const qtd = parseInt(document.getElementById(sabor).value) || 0;
      if (qtd > sabores[sabor].estoque) {
        alert(`Estoque insuficiente para ${sabor}`);
        erroEstoque = true;
        break;
      }
      total += qtd * sabores[sabor].preco;
    }
  
    if (!erroEstoque && total > 0) {
      document.getElementById('resultado').classList.remove('hidden');
      document.getElementById('resultado').innerText = `💰 Total a pagar: R$ ${total.toFixed(2)}`;
      document.getElementById('pagamento').classList.remove('hidden');
    }
  });
  
  document.getElementById('pagar').addEventListener('click', () => {
    const forma = document.getElementById('formaPagamento').value;
    if (!forma) {
      alert("Escolha uma forma de pagamento!");
      return;
    }
  
    for (let sabor in sabores) {
      const qtd = parseInt(document.getElementById(sabor).value) || 0;
      sabores[sabor].estoque -= qtd;
    }
  
    document.getElementById('mensagemFinal').classList.remove('hidden');
    document.getElementById('pagamento').classList.add('hidden');
    compraFinalizada = true;
  });
  