<div class="flex flex-grow justify-center">
	<div class="my-4 w-full max-w-screen-2xl px-2">
		<div class="text-center">
			<div class="text-lg font-bold">URL 줄이기</div>
			<div class="mt-3 text-gray-400">긴 URL을 단축시켜줍니다.</div>
		</div>

		<div class="divider"></div>

		<form class="grid grid-cols-1 gap-4">
			<label class="form-control">
				<div class="label">
					<span class="label-text"> URL<strong>(필수)</strong> </span>
				</div>
				<input
					class="input input-bordered"
					type="url"
					name="url"
					maxlength="200"
					autofocus
					autocomplete="off"
				/>
			</label>
			<label class="form-control"
				><div class="label"><span class="label-text">검색가능</span></div>
				<input class="toggle" type="checkbox" name="listed" value="true" /></label
			>
			<label class="form-control"
				><div class="label"><span class="label-text">제목</span></div>
				<input
					class="input input-bordered"
					type="text"
					name="title"
					maxlength="150"
					autocomplete="off"
				/></label
			>
			<label class="form-control"
				><div class="label"><span class="label-text">내용</span></div>
				<textarea
					class="textarea textarea-bordered"
					rows="5"
					name="body"
					maxlength="5000"
					autocomplete="off"
				></textarea>
			</label>
			<div class="grid grid-cols-1 gap-3 md:grid-cols-2">
				<button class="btn btn-outline" type="button">취소</button>
				<button class="btn btn-primary" type="submit"
					><i class="fa-solid fa-down-left-and-up-right-to-center"></i> 저장</button
				>
			</div>
		</form>
	</div>
</div>
