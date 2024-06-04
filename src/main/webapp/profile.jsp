<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Starlink - Perfil</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://unpkg.com/@phosphor-icons/web"></script>
<link rel="stylesheet" href="./styles/styles.css" />
<link rel="stylesheet" href="./styles/styles-profile.css" />
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<script>
function confirmDelete(event) {
    event.preventDefault();
    const userConfirmed = confirm("Voc� tem certeza que deseja excluir sua conta?");
    if (userConfirmed) {
        document.getElementById("delete-account-form").submit();
    }
}
</script>
</head>
<body class="w-full">
	<div class="w-full max-w-[353px] mx-auto flex flex-col items-center justify-center py-6">
		<a href="home" class="flex items-center justify-center"> 
			<img alt="" src="./images/logo-2.svg">
		</a>

		<div class="w-full h-px bg-neutral-700 my-4"></div>

		<h2 class="text-2xl text-neutral-200 font-bold">Perfil</h2>

		<div class="flex flex-col items-center justify-center gap-3 mb-5 mt-4">
			<div class="w-8 h-8 flex items-center justify-center">
				<img src="./images/user-icon.svg" alt="user icon" class="w-full" />
			</div>
			<span class="text-md font-bold text-neutral-200"><%=request.getAttribute("username")%></span>
		</div>

		<%
		String error = request.getParameter("error");
		if (error != null) {
		%>
		<div class="text-red-300 py-4 text-base flex gap-2 items-center">
			<i class="ph ph-warning-octagon" class="w-full h-full"></i> Erro:
			<%=error%>
		</div>
		<%
		}
		%>

		<form method="post" action="update-profile" class="w-full flex flex-col gap-3">
			<div class="flex flex-col gap-2">
				<label for="nome" class="text-lg text-neutral-200 font-semibold">Nome</label>
				<input value="<%=request.getAttribute("name")%>" name="name" type="text" id="nome"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu nome" required />
			</div>

			<div class="flex flex-col gap-2">
				<label for="email" class="text-lg text-neutral-200 font-semibold">Email</label>
				<input value="<%=request.getAttribute("email")%>" name="email" type="email" id="email"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu email" required />
			</div>

			<div class="flex flex-col gap-2">
				<label for="telefone" class="text-lg text-neutral-200 font-semibold">Telefone</label>
				<input value="<%=request.getAttribute("phone")%>" name="phone" type="tel" id="telefone"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu telefone" />
			</div>

			<div class="flex flex-col gap-2">
				<label for="address" class="text-lg text-neutral-200 font-semibold">Endere�o residencial</label> 
				<input value="<%=request.getAttribute("address")%>" name="address" type="text" id="address"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu endere�o residencial" />
			</div>

			<div class="w-full h-px bg-neutral-700 my-6"></div>

			<div class="flex flex-col gap-2">
				<label for="username" class="text-lg text-neutral-200 font-semibold">Nome de usu�rio</label> 
				<input value="<%=request.getAttribute("username")%>" name="username" type="text" id="username"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu nome de usu�rio" required />
			</div>

			<div class="flex flex-col gap-2">
				<label for="password" class="text-lg text-neutral-200 font-semibold">Senha</label>
				<input type="password" name="password" id="password"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite sua nova senha" required />
			</div>

			<div class="flex flex-col gap-2">
				<label for="birthdate" class="text-lg text-neutral-200 font-semibold">Data de nascimento</label> 
				<input value="<%=request.getAttribute("birthdate")%>" name="birthdate" type="date" id="birthdate"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400" />
			</div>

			<div class="flex flex-col gap-2">
				<label for="gender" class="text-lg text-neutral-200 font-semibold">G�nero</label>
				<input value="<%=request.getAttribute("gender")%>" name="gender" type="text" id="gender"
					class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
					placeholder="Digite seu g�nero" />
			</div>

			<button class="bg-neutral-200 h-[48px] font-bold mt-3 hover:bg-neutral-300 transition-all" type="submit">Salvar</button>
		</form>

		<form id="delete-account-form" method="post" action="delete-user-account" class="mt-8">
			<input type="hidden" name="_method" value="DELETE">
			<button type="submit" class="flex items-center gap-2 group cursor-pointer" onclick="confirmDelete(event)">
				<img src="./images/trash-icon.svg" alt="log-out icon" /> 
				<span class="text-red-300 group-hover:text-red-400 transition-all">Excluir conta</span>
			</button>
		</form>
	</div>
</body>
</html>
