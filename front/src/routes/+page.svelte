<script>
	import rq from '$lib/rq/rq.svelte';
</script>

<div class="flex flex-grow items-center justify-center">
	<div class="w-full max-w-lg px-2 py-6">
		<div class="text-center">
			<div class="text-lg font-bold">SURL, URL 단축 서비스</div>
			<div class="mt-3 text-gray-400">긴 URL을 단축시켜줍니다.</div>
		</div>

		<div class="divider"></div>

		<div>
			<div class="text-center text-lg font-bold">공지사항</div>

			<ul class="ml-5 mr-1 mt-4 list-disc text-gray-400">
				<li>
					<span class="text-red-500">소셜로그인</span>으로 로그인 후 이용 가능합니다.
				</li>
				<li>한번 생성된 단축 URL은 <span class="text-red-500">영원히</span> 유지됩니다.</li>
				<li>
					단축 URL의 PATH 부분은 오직 <span class="text-red-500">숫자</span>로만 구성되어 있습니다.
				</li>
				<li>단축된 URL 예시 : <span class="text-red-500">{rq.getFrontBaseUrl()}/3342</span></li>
			</ul>
		</div>
	</div>
</div>
