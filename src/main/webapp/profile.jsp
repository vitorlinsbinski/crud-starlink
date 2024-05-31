<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Perfil</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="./styles/styles.css" />
    <link rel="stylesheet" href="./styles/styles-profile.css" />
  </head>
  <body class="w-full">
    <div
      class="w-full max-w-[353px] mx-auto flex flex-col items-center justify-center py-6">
      <a href = "home" class = "flex items-center justify-center">
      	<img alt="" src="./images/logo-2.svg">
      </a>
      
      <div class="w-full h-px bg-neutral-700 my-4"></div>
      
      <h2 class = "text-2xl text-neutral-200 font-bold">Perfil</h2>
     
      
      <div class="flex flex-col items-center justify-center gap-3 mb-5 mt-4">
        <div class="w-8 h-8 flex items-center justify-center">
          <img src="./images/user-icon.svg" alt="user icon" class="w-full" />
        </div>

        <span class="text-md font-bold text-neutral-200"><%= request.getAttribute("username") %></span>
      </div>

      <form action="" class="w-full flex flex-col gap-3">
        <div class="flex flex-col gap-2">
          <label for="nome" class="text-lg text-neutral-200 font-semibold"
            >Nome</label
          >
          <input
            value="<%= request.getAttribute("name") %>"
            type="text"
            id="nome"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu nome"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="email" class="text-lg text-neutral-200 font-semibold"
            >Email</label
          >
          <input
            value="<%= request.getAttribute("email") %>"
            type="email"
            id="email"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua email"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="telefone" class="text-lg text-neutral-200 font-semibold"
            >Telefone</label
          >
          <input
            value="<%= request.getAttribute("phone") %>"
            type="tel"
            id="telefone"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua telefone" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="address" class="text-lg text-neutral-200 font-semibold"
            >Endereço residencial</label
          >
          <input
            value="<%= request.getAttribute("address") %>"
            type="text"
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
            value="<%= request.getAttribute("username") %>"
            type="text"
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
            id="password"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua nova senha"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="birthdate" class="text-lg text-neutral-200 font-semibold"
            >Data de nascimento</label
          >
          <input
            value="<%= request.getAttribute("birthdate") %>"
            type="date"
            id="birthdate"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="gender" class="text-lg text-neutral-200 font-semibold"
            >Gênero</label
          >
          <input
            value="<%= request.getAttribute("gender") %>"
            type="text"
            id="gender"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu gênero" />
        </div>

        <button
          class="bg-neutral-200 h-[48px] font-bold mt-3 hover:bg-neutral-300 transition-all"
          type="submit">
          Salvar
        </button>
      </form>

      <button class="flex items-center gap-2 mt-8 group">
        <img src="./images/trash-icon.svg" alt="log-out icon" />
        <span class="text-red-300 group-hover:text-red-400 transition-all"
          >Excluir conta</span
        >
      </button>
    </div>
  </body>
</html>
