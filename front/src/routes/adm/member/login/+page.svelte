<script lang="ts">
	async function submitLoginForm(this: HTMLFormElement) {
		const form: HTMLFormElement = this;

		form.username.value = form.username.value.trim();

		if (form.username.value.length === 0) {
			alert('아이디를 입력해주세요.');
			form.username.focus();

			return;
		}

		form.password.value = form.password.value.trim();

		if (form.password.value.length === 0) {
			alert('비밀번호를 입력해주세요.');
			form.password.focus();

			return;
		}
	}
</script>

<form on:submit|preventDefault={submitLoginForm}>
	<div>
		<div>아이디</div>
		<input type="text" name="username" placeholder="아이디" />
	</div>

	<div>
		<div>비밀번호</div>
		<input type="password" name="password" placeholder="비밀번호" />
	</div>

	<div>
		<div>로그인</div>
		<button type="submit">로그인</button>
	</div>
</form>
