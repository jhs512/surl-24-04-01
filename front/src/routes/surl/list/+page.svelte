<script lang="ts">
	import { page } from '$app/stores';

	import rq from '$lib/rq/rq.svelte';
	import { prettyDateTime } from '$lib/utils/';
	import type { components } from '$lib/backend/apiV1/schema';
	import Pagination from '$lib/components/Pagination.svelte';
	import type { KwTypeV3 } from '$lib/types/';

	let surls: components['schemas']['SurlDto'][] = $state([]);

	async function load() {
		if (import.meta.env.SSR) throw new Error('CSR ONLY');

		const kw = $page.url.searchParams.get('kw') ?? '';
		const kwType = ($page.url.searchParams.get('kwType') ?? 'ALL') as KwTypeV3;
		const page_ = parseInt($page.url.searchParams.get('page') ?? '1');

		const { data } = await rq.apiEndPoints().GET('/api/v1/surls', {
			params: {
				query: {
					kw,
					kwType,
					page: page_
				}
			}
		});

		surls = data!.data.itemPage.content;

		return data!;
	}
</script>

<div class="flex flex-grow justify-center">
	<div class="my-4 w-full max-w-screen-2xl px-2">
		<div class="text-center">
			<div class="text-lg font-bold">공개글</div>
			<div class="mt-3 text-gray-400">공개된 글 입니다.</div>
		</div>

		<div class="divider"></div>

		{#await load()}
			<p>loading...</p>
		{:then { data: { itemPage } }}
			<div class="mt-4 flex flex-wrap items-center gap-2">
				<div class="badge badge-outline">
					검색결과 : {itemPage.totalElementsCount.toLocaleString()}건
				</div>

				<div class="flex-grow"></div>

				<div class="flex gap-2">
					<button
						class="btn btn-primary"
						onclick={() => {
          const modal = document.querySelector('#search_modal_1') as HTMLDialogElement;
          modal.showModal();
  
          const inputKw = modal.querySelector('input[name="kw"]') as HTMLInputElement;
          inputKw.focus();
        }}
					>
						<i class="fa-solid fa-magnifying-glass"></i> 검색
						{#if $page.url.searchParams.get('kw')}
							<span class="text-sm text-gray-400"
								>(검색어 : {$page.url.searchParams.get('kw')})</span
							>
						{/if}
					</button>

					{#if $page.url.searchParams.get('kw')}
						<a class="btn" href={$page.url.pathname}>
							<i class="fa-solid fa-xmark"></i> 검색어 지우기
						</a>
					{/if}
				</div>
			</div>

			<dialog id="search_modal_1" class="modal">
				<div class="modal-box">
					<h3 class="text-lg font-bold">검색</h3>
					<form
						action={$page.url.pathname}
						class="bg-base grid grid-cols-1 gap-4 rounded"
						onsubmit={() => {
            const modal = document.querySelector('#search_modal_1') as HTMLDialogElement;
            modal.close();
          }}
					>
						<div class="form-control">
							<!-- svelte-ignore a11y-label-has-associated-control -->
							<label class="label">
								<span class="label-text">검색필터</span>
							</label>

							<select
								name="kwType"
								class="select select-bordered"
								value={$page.url.searchParams.get('kwType') ?? 'ALL'}
							>
								<option value="ALL">전체</option>
								<option value="TITLE">제목</option>
								<option value="BODY">내용</option>
								<option value="URL">URL</option>
							</select>
						</div>

						<div class="form-control">
							<!-- svelte-ignore a11y-label-has-associated-control -->
							<label class="label">
								<span class="label-text">검색어</span>
							</label>

							<input
								placeholder="검색어"
								class="input input-bordered"
								name="kw"
								type="search"
								value={$page.url.searchParams.get('kw') ?? ''}
								autocomplete="off"
							/>
						</div>

						<div>
							<button class="btn btn-primary btn-block gap-1">
								<i class="fa-solid fa-magnifying-glass"></i>
								<span>검색</span>
							</button>
						</div>
					</form>
				</div>
				<form method="dialog" class="modal-backdrop">
					<button>close</button>
				</form>
			</dialog>

			<div class="mt-4">
				<Pagination page={itemPage} />
			</div>

			<ul class="mt-4 grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3">
				{#each surls as surl}
					<li>
						<div class="card bg-base-100 shadow">
							<div class="card-body">
								<div class="detail grid grid-cols-[repeat(auto-fit,minmax(100px,1fr))] gap-5">
									<a
										href="/surl/{surl.id}"
										class="col-span-full flex aspect-video flex-wrap items-center justify-center rounded bg-gray-700 p-3 text-gray-100"
									>
										<div class="flex flex-wrap gap-1">
											<div class="flex gap-1">
												<span>🗒️</span>
												<span>{surl.title}</span>
											</div>
											<div class="flex-grow text-right italic text-gray-300">
												<span>by</span>
												<span>{surl.authorId}</span>
											</div>
										</div>
									</a>

									<div class="form-control">
										<!-- svelte-ignore a11y-label-has-associated-control -->
										<label class="label">
											<span class="label-text">번호</span>
										</label>
										<div>{surl.id}</div>
									</div>

									<div class="form-control">
										<!-- svelte-ignore a11y-label-has-associated-control -->
										<label class="label">
											<span class="label-text">작성일</span>
										</label>
										<div>{prettyDateTime(surl.createDate)}</div>
									</div>
								</div>

								<a href="/p/{surl.id}" class="btn btn-primary"
									><i class="fa-solid fa-book-open-reader"></i> 글 보기</a
								>
							</div>
						</div>
					</li>
				{/each}
			</ul>

			<div class="mb-4 mt-4">
				<Pagination page={itemPage} />
			</div>
		{/await}
	</div>
</div>
