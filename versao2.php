<?php
// Definindo os sabores e seus respectivos estoques e preços
$sabores = [
  'maracuja' => ['estoque' => 40, 'preco' => 3.00],
  'morango' => ['estoque' => 30, 'preco' => 3.50],
  'goiaba' => ['estoque' => 25, 'preco' => 2.50],
  'chocolate' => ['estoque' => 50, 'preco' => 4.00],
  'coco' => ['estoque' => 35, 'preco' => 3.00],
  'limao' => ['estoque' => 20, 'preco' => 2.00],
];

// Verificando se o formulário foi enviado
$total = 0;
$quantidades = [];
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  foreach ($sabores as $sabor => $dados) {
    $quantidade = isset($_POST[$sabor]) ? intval($_POST[$sabor]) : 0;
    if ($quantidade <= $dados['estoque']) {
      $quantidades[$sabor] = $quantidade;
      $total += $quantidade * $dados['preco'];
    }
  }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Picolés Geladinho</title>
  <link rel="stylesheet" href="versao2.css">
</head>
<body>
  <div class="container">
    <h1>🍧 Escolha seus Picolés</h1>
    <form method="POST" action="versao2.php">
      <div class="sabores">
        <?php foreach ($sabores as $sabor => $dados): ?>
          <div class="sabor-card">
            <h3><?php echo ucfirst($sabor); ?></h3>
            <p>Estoque: <?php echo $dados['estoque']; ?></p>
            <p>Preço: R$ <?php echo number_format($dados['preco'], 2, ',', '.'); ?></p>
            <input type="number" name="<?php echo $sabor; ?>" min="0" max="<?php echo $dados['estoque']; ?>" placeholder="Qtd. desejada" value="<?php echo isset($quantidades[$sabor]) ? $quantidades[$sabor] : 0; ?>">
          </div>
        <?php endforeach; ?>
      </div>

      <button type="submit">Calcular Total</button>
    </form>

    <?php if ($total > 0): ?>
      <div id="resultado">
        <h2>Total a pagar: R$ <?php echo number_format($total, 2, ',', '.'); ?></h2>
        <div id="pagamento">
          <h3>Escolha a forma de pagamento</h3>
          <select name="formaPagamento">
            <option value="">Selecione</option>
            <option value="cartao">Cartão</option>
            <option value="pix">PIX</option>
            <option value="dinheiro">Dinheiro</option>
          </select>
          <button type="submit">Pagar</button>
        </div>
      </div>
    <?php endif; ?>

    <?php if (isset($_POST['formaPagamento']) && !empty($_POST['formaPagamento'])): ?>
      <div id="mensagemFinal">
        ✅ Compra Finalizada! Obrigado pelo pagamento via <?php echo $_POST['formaPagamento']; ?>!
      </div>
    <?php endif; ?>
  </div>

  <script src="versao2.js"></script>
</body>
</html>
