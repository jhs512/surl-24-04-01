<script lang="ts">
	import { onMount } from 'svelte';
	import { page } from '$app/stores';

	import '../app.pcss';

	import rq from '$lib/rq/rq.svelte';

	const { children } = $props();

	onMount(() => {
		rq.initAuth();
	});
</script>

<header class="navbar sticky top-0 z-10 bg-base-100 shadow">
	<div class="flex-none">
		<div class="dropdown">
			<div tabindex="0" role="button" class="btn btn-circle btn-ghost">
				<svg
					xmlns="http://www.w3.org/2000/svg"
					class="h-5 w-5"
					fill="none"
					viewBox="0 0 24 24"
					stroke="currentColor"
					><path
						stroke-linecap="round"
						stroke-linejoin="round"
						stroke-width="2"
						d="M4 6h16M4 12h16M4 18h7"
					/></svg
				>
			</div>
			<!-- svelte-ignore a11y-no-noninteractive-tabindex -->
			<ul
				tabindex="0"
				class="menu dropdown-content menu-sm z-[1] mt-3 w-52 rounded-box bg-base-100 p-2 shadow"
			>
				<li>
					<a href="/surl/list"><i class="fa-solid fa-list-check"></i> 공개</a>
				</li>

				{#if rq.isLogin()}
					<li>
						<a href="/surl/mine"><i class="fa-solid fa-list-check"></i> 히스토리</a>
					</li>
					<li>
						<a href="/surl/create"><i class="fa-solid fa-plus"></i> URL 줄이기</a>
					</li>
				{/if}

				{#if rq.isAdmPage($page)}
					<li><a href="/"><i class="fa-solid fa-house"></i> 홈</a></li>
				{/if}

				{#if rq.isUsrPage($page) && rq.isAdmin()}
					<li><a href="/adm"><i class="fa-solid fa-gauge"></i> 관리자</a></li>
				{/if}
			</ul>
		</div>
	</div>

	<div class="flex-1">
		{#if rq.isUsrPage($page)}
			<a href="/" class="text-md btn btn-ghost">SURL</a>
		{/if}
		{#if rq.isAdmPage($page)}
			<a href="/adm" class="text-md btn btn-ghost">SURL ADMIN</a>
		{/if}
	</div>

	<div class="flex-none">
		<div class="dropdown dropdown-end">
			{#if rq.isLogout()}
				<button class="btn btn-square btn-ghost">
					<svg
						xmlns="http://www.w3.org/2000/svg"
						fill="none"
						viewBox="0 0 24 24"
						class="inline-block h-5 w-5 stroke-current"
						><path
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"
						></path></svg
					>
				</button>
			{/if}
			{#if rq.isLogin()}
				<div tabindex="0" role="button" class="avatar btn btn-circle btn-ghost">
					<div class="w-10 rounded-full">
						<img src={rq.member.profileImgUrl} alt="" />
					</div>
				</div>
			{/if}
			<!-- svelte-ignore a11y-no-noninteractive-tabindex -->
			<ul
				tabindex="0"
				class="menu dropdown-content menu-sm z-[1] mt-3 w-52 rounded-box bg-base-100 p-2 shadow"
			>
				{#if rq.isLogout()}
					<li>
						<a href="/member/login"><i class="fa-solid fa-right-to-bracket"></i> 로그인 & 가입</a>
					</li>
				{/if}
				{#if rq.isLogin()}
					<li>
						<a href="/member/me"><i class="fa-solid fa-user"></i> {rq.member.name}</a>
					</li>
					<li>
						<button on:click={() => rq.logoutAndRedirect('/')}
							><i class="fa-solid fa-right-from-bracket"></i> 로그아웃</button
						>
					</li>
				{/if}
			</ul>
		</div>
	</div>
</header>
<main class="flex flex-grow flex-col">{@render children()}</main>
<footer></footer>
