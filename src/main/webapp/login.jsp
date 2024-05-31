<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="./styles/styles.css" />
    <link rel="stylesheet" href="./styles/styles-home.css" />
  </head>
  <body class="w-full">
    <div
      class="flex flex-col max-w-6xl mx-auto h-screen justify-center px-4 py-4 relative">
      <div class="mb-7 absolute top-10 right-0 py-4 flex justify-end">
        <img src="./images/logo-1.svg" alt="" />
      </div>

      <div class="pb-10">
        <div>
          <h1 class="text-gray-100 text-3xl">
            Bem-vindo à <strong class="block underline">Starlink</strong>
          </h1>

          <span class="text-neutral-400 text-base mt-4 inline-block"
            >Acesse sua conta</span
          >
        </div>

        <form
          method="post"
          action="login"
          id="login-form"
          class="w-full max-w-[344px] mt-8 flex flex-col gap-5">
          <div class="flex flex-col gap-2">
            <label for="username" class="text-lg text-neutral-200 font-semibold"
              >Nome de usuário</label
            >
            <input
              type="text"
              name = "username"
              id="username"
              class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
              placeholder="Digite seu nome de usuário" 
              required/>
          </div>

          <div class="flex flex-col gap-2">
            <label for="password" class="text-lg text-neutral-200 font-semibold"
              >Senha</label
            >
            <input
              type="password"
              name = "password"
              id="password"
              class="h-[48px] bg-neutral-900 border border-neutral-700 border-neutral-300 px-5 text-sm text-neutral-200 placeholder:text-neutral-400"
              placeholder="Digite sua senha" 
              required/>
          </div>

          <button
            class="bg-neutral-200 h-[48px] font-bold mt-3 hover:bg-neutral-300 transition-all"
            type="submit">
            Entrar
          </button>
        </form>
      </div>

      <div class="w-[344px] h-px bg-neutral-700"></div>

      <div class="flex flex-col w-full max-w-[344px]">
        <p class="text-neutral-200 text-base mt-4 inline-block">
          Não tem uma conta?
        </p>

        <a
          href="register.jsp"
          class="h-[48px] font-bold mt-3 transition-all text-neutral-200 border border-neutral-300 hover:bg-neutral-900 flex items-center justify-center"
          >Cadastrar</a
        >
      </div>
    </div>
  </body>
</html>
