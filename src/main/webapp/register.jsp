<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cadastro</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/@phosphor-icons/web"></script>
    <link rel="stylesheet" href="./styles/styles.css" />
    <link rel="stylesheet" href="./styles/styles-register.css" />
  </head>
  <body class="h-screen w-full">
    <div
      class="w-full max-w-[400px] mx-auto flex items-center flex-col justify-center mt-10 pb-10 px-4">
      <h1 class="text-gray-100 text-3xl text-center">
        Faça o seu <strong class="block underline">cadastro</strong>
      </h1>
      
		<%
			String error = request.getParameter("error");
			if (error != null) {
			%>
			    <div class="text-red-300 mt-4 text-base flex items-center justify-center gap-2">
			    	<i class="ph ph-warning-octagon" class = "w-full h-full"></i>

			        Erro: <%= error %>
			    </div>
			<%
		}
		%>

      <form
        method="post"
        action="register"
        id="login-form"
        class="w-full flex flex-col gap-4 mt-5">
        <div class="flex flex-col gap-2">
          <label for="name" class="text-lg text-neutral-200 font-semibold"
            >Nome</label
          >
          <input
            type="text"
            name="name"
            id="name"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu nome"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="email" class="text-lg text-neutral-200 font-semibold"
            >Email</label
          >
          <input
            type="email"
            name="email"
            id="email"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua email"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="phone" class="text-lg text-neutral-200 font-semibold"
            >Telefone</label
          >
          <input
            type="tel"
            name="phone"
            id="phone"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua telefone" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="address" class="text-lg text-neutral-200 font-semibold"
            >Endereço residencial</label
          >
          <input
            type="text"
            name="address"
            id="address"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu endereço residencial" />
        </div>

        <div class="w-full h-px bg-neutral-700 my-6"></div>

        <div class="flex flex-col gap-2">
          <label for="username" class="text-lg text-neutral-200 font-semibold"
            >Nome de usuário</label
          >
          <input
            type="text"
            
            name="username"
            id="username"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu nome de usuário"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="password" class="text-lg text-neutral-200 font-semibold"
            >Senha</label
          >
          <input
            type="password"
            name="password"
            id="password"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite uma senha"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="birthdate" class="text-lg text-neutral-200 font-semibold"
            >Data de nascimento</label
          >
          <input
            type="date"
            name="birthdate"
            id="birthdate"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="gender" class="text-lg text-neutral-200 font-semibold"
            >Gênero</label
          >
          <input
            type="text"
            name="gender"
            id="gender"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu gênero" />
        </div>

        <button
          class="bg-neutral-200 h-[48px] font-bold mt-3 hover:bg-neutral-300 transition-all"
          type="submit">
          Cadastrar
        </button>
      </form>
    </div>
  </body>
</html>
