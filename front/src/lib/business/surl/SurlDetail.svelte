<script lang="ts">
	import { prettyDateTime } from '$lib/utils/index';
	import type { components } from '$lib/backend/apiV1/schema';

	const { surl }: { surl: components['schemas']['SurlDto'] } = $props();
</script>

<div class="flex flex-grow items-center justify-center">
	<div class="mt-4 w-full px-2">
		<div class="card bg-base-100 shadow">
			<div class="card-body">
				<div class="detail grid grid-cols-[repeat(auto-fit,minmax(100px,1fr))] gap-3">
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
							<span class="label-text">생성일</span>
						</label>
						<div>{prettyDateTime(surl.createDate)}</div>
					</div>

					<div class="form-control">
						<!-- svelte-ignore a11y-label-has-associated-control -->
						<label class="label">
							<span class="label-text">작성자</span>
						</label>
						<div>{surl.authorId}</div>
					</div>

					<div class="form-control col-span-full">
						<!-- svelte-ignore a11y-label-has-associated-control -->
						<label class="label">
							<span class="label-text">제목</span>
						</label>
						<div>
							{surl.title}
						</div>
					</div>

					<div class="form-control col-span-full">
						<!-- svelte-ignore a11y-label-has-associated-control -->
						<label class="label">
							<span class="label-text">내용</span>
						</label>
						<div>
							{surl.body}
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
