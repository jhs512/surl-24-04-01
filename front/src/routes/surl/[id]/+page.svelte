<script lang="ts">
	import SurlDetail from '$lib/business/surl/SurlDetail.svelte';
	import SurlCommentWriteAndList from '$lib/business/surlComment/SurlCommentWriteAndList.svelte';

	const { data } = $props();
	const { surl } = data;
</script>

<svelte:head>
	<title>{surl.title}</title>
	<meta name="description" content={surl.body} />
</svelte:head>

<div class="flex flex-grow items-center justify-center">
	<div class="mt-4 w-full px-2">
		<SurlDetail {surl} />
		<SurlCommentWriteAndList {surl} />
	</div>
</div>
