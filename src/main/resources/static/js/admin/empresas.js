$(document).ready(function () {
    $(".form-deletar").on("submit", function (event) {
        event.preventDefault();

        const form = this;

        Swal.fire({
            title: "Confirmar exclusão",
            text: "Deseja realmente deletar esta empresa?",
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
            if (!result.isConfirmed) {
                return;
            }

            $.ajax({
                url: form.action,
                type: "POST",
                data: $(form).serialize(),
                success: function () {
                    location.reload();
                },
                error: function () {
                    Swal.fire({
                        title: "Erro ao deletar",
                        text: "Não é possível deletar esta empresa pois ela possui referências.",
                        icon: "error",
                        iconColor: "#ef4444",
                        background: "#111827",
                        color: "#dbeafe",
                        confirmButtonText: "Ok",
                        confirmButtonColor: "#dc2626",
                    });
                }
            });
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
    document.getElementById("editar-razao-social").value = button.dataset.razaoSocial;
    document.getElementById("editar-nome-fantasia").value = button.dataset.nomeFantasia;
    document.getElementById("editar-cnpj").value = button.dataset.cnpj;
    document.getElementById("editar-telefone").value = button.dataset.telefone;
    document.getElementById("editar-email").value = button.dataset.email;

    document.getElementById("editar-modal").classList.add("active");
}

function fecharModalEditar() {
    document.getElementById("editar-modal").classList.remove("active");
}