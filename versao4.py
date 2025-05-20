import tkinter as tk
from tkinter import messagebox

# Definindo os sabores com seus respectivos estoques e preços
SABORES = ["Maracujá", "Morango", "Goiaba", "Chocolate", "Coco", "Limão"]
ESTOQUES = [40, 30, 25, 50, 35, 20]
PRECO = [3.00, 3.50, 2.50, 4.00, 3.00, 2.00]

class PicolésApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Sistema de Picolés")
        self.root.geometry("350x400")

        self.inputs = []

        # Título
        title = tk.Label(self.root, text="Escolha seus Picolés", font=("Arial", 16, "bold"))
        title.pack(pady=10)

        # Criando campos para os sabores
        for i in range(len(SABORES)):
            frame = tk.Frame(self.root)
            frame.pack(fill="x", pady=5)

            label = tk.Label(frame, text=f"{SABORES[i]} - Estoque: {ESTOQUES[i]} - Preço: R$ {PRECO[i]:.2f}")
            label.pack(side="left")

            input_field = tk.Entry(frame)
            input_field.insert(0, "0")
            input_field.pack(side="right")
            self.inputs.append(input_field)

        # Botão para calcular o total
        calcular_button = tk.Button(self.root, text="Calcular Total", command=self.calcular_total)
        calcular_button.pack(pady=10)

        # Label para mostrar o total
        self.total_label = tk.Label(self.root, text="Total a pagar: R$ 0.00", font=("Arial", 12, "bold"))
        self.total_label.pack(pady=10)

        # Botão para pagamento
        self.pagar_button = tk.Button(self.root, text="Pagar", state="disabled", command=self.finalizar_compra)
        self.pagar_button.pack(pady=10)

    def calcular_total(self):
        total = 0
        erro_estoque = False

        # Verificando a quantidade de picolés e calculando o total
        for i in range(len(SABORES)):
            try:
                qtd = int(self.inputs[i].get())
                if qtd < 0:
                    messagebox.showerror("Erro", f"A quantidade para {SABORES[i]} não pode ser negativa.")
                    return
                if qtd > ESTOQUES[i]:
                    messagebox.showerror("Erro", f"Estoque insuficiente para {SABORES[i]}.")
                    erro_estoque = True
                    break
                total += qtd * PRECO[i]
            except ValueError:
                messagebox.showerror("Erro", f"Por favor, insira um número válido para {SABORES[i]}.")
                return

        if not erro_estoque:
            self.total_label.config(text=f"Total a pagar: R$ {total:.2f}")
            self.pagar_button.config(state="normal")

    def finalizar_compra(self):
        messagebox.showinfo("Compra Finalizada", "Compra Finalizada! Obrigado!")
        self.root.quit()  # Fecha a aplicação

# Criando a janela principal
root = tk.Tk()
app = PicolésApp(root)

# Iniciando a aplicação
root.mainloop()
