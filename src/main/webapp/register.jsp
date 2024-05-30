<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cadastro</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="./styles/styles.css" />
    <link rel="stylesheet" href="./styles/styles-register.css" />
  </head>
  <body class="h-screen w-full">
    <div
      class="w-full max-w-[400px] mx-auto flex items-center flex-col justify-center mt-10 pb-10 px-4">
      <h1 class="text-gray-100 text-3xl text-center">
        Fa�a o seu <strong class="block underline">cadastro</strong>
      </h1>

      <form
        method="get"
        action="#"
        id="login-form"
        class="w-full flex flex-col gap-4 mt-5">
        <div class="flex flex-col gap-2">
          <label for="nome" class="text-lg text-neutral-200 font-semibold"
            >Nome</label
          >
          <input
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
            type="tel"
            id="telefone"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite sua telefone" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="address" class="text-lg text-neutral-200 font-semibold"
            >Endere�o residencial</label
          >
          <input
            type="text"
            id="address"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu endere�o residencial" />
        </div>

        <div class="w-full h-px bg-neutral-700 my-6"></div>

        <div class="flex flex-col gap-2">
          <label for="username" class="text-lg text-neutral-200 font-semibold"
            >Nome de usu�rio</label
          >
          <input
            type="text"
            id="username"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu nome de usu�rio"
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
            placeholder="Digite uma senha"
            required />
        </div>

        <div class="flex flex-col gap-2">
          <label for="birthday" class="text-lg text-neutral-200 font-semibold"
            >Data de nascimento</label
          >
          <input
            type="date"
            id="birthday"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400" />
        </div>

        <div class="flex flex-col gap-2">
          <label for="gender" class="text-lg text-neutral-200 font-semibold"
            >G�nero</label
          >
          <input
            type="text"
            id="gender"
            class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
            placeholder="Digite seu g�nero" />
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
