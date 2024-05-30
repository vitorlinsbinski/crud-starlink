<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="./styles/styles.css" />
  </head>
  <body class="h-screen w-full">
    <div
      class="w-full max-w-[353px] mx-auto flex flex-col items-center justify-center py-6">
      <a
        href="profile.html"
        class="flex items-center justify-center gap-2 mb-6 group">
        <img src="./images/user-icon.svg" alt="user-icon" />

        <strong
          class="text-lg text-slate-200 group-hover:text-slate-300 transition-all"
          >vitorlinsbinski</strong
        >
      </a>

      <div class="flex items-center justify-center flex-col gap-6">
        <div>
          <img src="./images/starlink-image.png" alt="starlink-image" />
        </div>

        <div>
          <img src="./images/logo-2.svg" alt="starlink-logo" />
        </div>
      </div>

      <p class="text-base text-neutral-300 mt-8">Emitir relatórios</p>

      <ul class="w-full mt-4 flex flex-col gap-4">
        <li
          class="w-full h-[50px] bg-neutral-900 border border-neutral-700 px-4 hover:bg-neutral-800 transition-all cursor-pointer flex items-center">
          <a href="" class="flex items-center justify-between w-full">
            <div class="flex items-center gap-3">
              <div>
                <img src="./images/browser-icon.svg" alt="browser icon" />
              </div>

              <span class="text-base text-slate-300">Navegação online</span>
            </div>

            <div>
              <img src="./images/arrow-right-icon.svg" alt="arrow right icon" />
            </div>
          </a>
        </li>

        <li
          class="w-full h-[50px] bg-neutral-900 border border-neutral-700 px-4 hover:bg-neutral-800 transition-all cursor-pointer flex items-center">
          <a href="" class="flex items-center justify-between w-full">
            <div class="flex items-center gap-3">
              <div>
                <img src="./images/pin-icon.svg" alt="pin icon" />
              </div>

              <span class="text-base text-slate-300"
                >Localização geográfica</span
              >
            </div>

            <div>
              <img src="./images/arrow-right-icon.svg" alt="arrow right icon" />
            </div>
          </a>
        </li>

        <li
          class="w-full h-[50px] bg-neutral-900 border border-neutral-700 px-4 hover:bg-neutral-800 transition-all cursor-pointer flex items-center">
          <a href="" class="flex items-center justify-between w-full">
            <div class="flex items-center gap-3">
              <div>
                <img src="./images/group-iconm.svg" alt="group icon" />
              </div>

              <span class="text-base text-slate-300">Informações sociais</span>
            </div>

            <div>
              <img src="./images/arrow-right-icon.svg" alt="arrow right icon" />
            </div>
          </a>
        </li>

        <li
          class="w-full h-[50px] bg-neutral-900 border border-neutral-700 px-4 hover:bg-neutral-800 transition-all cursor-pointer flex items-center">
          <a href="" class="flex items-center justify-between w-full">
            <div class="flex items-center gap-3">
              <div>
                <img
                  src="./images/credit-card-icon.svg"
                  alt="credit-card icon" />
              </div>

              <span class="text-base text-slate-300"
                >Pagamentos e transações</span
              >
            </div>

            <div>
              <img src="./images/arrow-right-icon.svg" alt="arrow right icon" />
            </div>
          </a>
        </li>

        <li
          class="w-full h-[50px] bg-neutral-900 border border-neutral-700 px-4 hover:bg-neutral-800 transition-all cursor-pointer flex items-center">
          <a href="" class="flex items-center justify-between w-full">
            <div class="flex items-center gap-3">
              <div>
                <img src="./images/tool-icon.svg" alt="tool icon" />
              </div>

              <span class="text-base text-slate-300">Informações técnicas</span>
            </div>

            <div>
              <img src="./images/arrow-right-icon.svg" alt="arrow right icon" />
            </div>
          </a>
        </li>
      </ul>

      <button class="flex items-center gap-2 mt-10 group">
        <img src="./images/log-out-icon.svg" alt="log-out icon" />
        <span class="text-red-300 group-hover:text-red-400 transition-all"
          >Log out</span
        >
      </button>
    </div>
  </body>
</html>
