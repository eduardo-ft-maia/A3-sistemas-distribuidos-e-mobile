$(document).ready(function () {
    $(".form-deletar").on("submit", function (event) {
        event.preventDefault();

        const form = this;

        Swal.fire({
            title: "Confirmar exclusão",
            text: "Deseja realmente deletar este usuário?",
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
                        text: "Não foi possível deletar este usuário.",
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
    document.getElementById("editar-username").value = button.dataset.username;
    document.getElementById("editar-role").value = button.dataset.role;
    document.getElementById("editar-empresa-id").value = button.dataset.empresa;

    document.getElementById("editar-password").value = "";

    document.getElementById("editar-modal").classList.add("active");
}

function fecharModalEditar() {
    document.getElementById("editar-modal").classList.remove("active");
}