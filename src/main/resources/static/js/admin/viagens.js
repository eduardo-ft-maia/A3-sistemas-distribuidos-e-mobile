$(document).ready(function () {
    $(".form-deletar").on("submit", function (event) {
        event.preventDefault();

        const form = this;

        Swal.fire({
            title: "Confirmar exclusão",
            text: "Deseja realmente deletar esta viagem?",
            icon: "warning",
            iconColor: "#ef4444",
            background: "#111827",
            color: "#dbeafe",
            backdrop: "rgba(0, 0, 0, 0.55)",
            showCancelButton: true,
            confirmButtonText: "Deletar",
            cancelButtonText: "Cancelar",
            confirmButtonColor: "#dc2626",
            cancelButtonColor: "#1e293b",
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
    });
});

function abrirModalCadastrar() {
    document.getElementById("cadastrar-modal").classList.add("active");
}

function fecharModalCadastrar() {
    document.getElementById("cadastrar-modal").classList.remove("active");
}

function abrirModalEditar(button) {
    document.getElementById("editar-id").value = button.dataset.id;
    document.getElementById("editar-origem").value = button.dataset.origem;
    document.getElementById("editar-destino").value = button.dataset.destino;
    document.getElementById("editar-saida").value = button.dataset.saida;
    document.getElementById("editar-chegada").value = button.dataset.chegada;
    document.getElementById("editar-preco").value = button.dataset.preco;
    document.getElementById("editar-distancia").value = button.dataset.distancia;
    document.getElementById("editar-modal").classList.add("active");
}

function fecharModalEditar() {
    document.getElementById("editar-modal").classList.remove("active");
}