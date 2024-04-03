<script lang="ts">
	import type { components } from '$lib/backend/apiV1/schema';

	import rq from '$lib/rq/rq.svelte';

	import SurlCommentWrite from '$lib/business/surlComment/SurlCommentWrite.svelte';
	import SurlCommentList from '$lib/business/surlComment/SurlCommentList.svelte';

	const { surl: _surl }: { surl: components['schemas']['SurlDto'] } = $props();
	const surl = $state(_surl);

	let surlComments: components['schemas']['SurlCommentDto'][] = $state([]);

	function addSurlComment(surlComment: components['schemas']['SurlCommentDto']) {
		surlComments.unshift(surlComment);
	}

	function deleteSurlComment(surlComment: components['schemas']['SurlCommentDto']) {
		surlComments.splice(surlComments.indexOf(surlComment), 1);
	}

	function modifySurlComment(surlComment: components['schemas']['SurlCommentDto']) {
		const idx = surlComments.findIndex((v) => v.id == surlComment.id);

		surlComments[idx] = surlComment;
	}

	async function loadSurlComments() {
		if (import.meta.env.SSR) throw new Error('CSR ONLY');

		const { data } = await rq.apiEndPoints().GET('/api/v1/surlComments/{surlId}', {
			params: {
				path: {
					surlId: surl.id
				}
			}
		});

		surlComments = data!.data.items;
	}
</script>

<h1>댓글</h1>

<div><span class="text-gray-400">{surl.title}</span> 에 대한 댓글</div>

{#await loadSurlComments()}
	loading...
{:then}
	{JSON.stringify(surlComments)}
	<!-- <SurlCommentWrite {surl} {addSurlComment} />
	<SurlCommentList
		{surl}
		{surlComments}
		{deleteSurlComment}
		{modifySurlComment}
		{increaseSurlCommentChildrenCount}
	/> -->
{:catch error}
	{error.msg}
{/await}
