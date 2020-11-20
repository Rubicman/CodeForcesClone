<template>
    <div class="register form-box">
        <div class="header">Registration</div>
        <div class="body">
            <form @submit.prevent="onRegister">
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input id="login" name="login" v-model="login"/>
                    </div>
                </div>

                <div class="field">
                    <div class="name">
                        <label for="name">Name</label>
                    </div>
                    <div class="value">
                        <input id="name" name="name" v-model="name"/>
                    </div>
                </div>

                <div class="field">
                    <div class="name">
                        <label for="password">Password</label>
                    </div>
                    <div class="value">
                        <input id="password" type="password" name="password" v-model="password"/>
                    </div>
                </div>

                <div class="field">
                    <div class="name">
                        <label for="passwordConfirmation">Password Confirmation</label>
                    </div>
                    <div class="value">
                        <input id="passwordConfirmation" type="password" name="passwordConfirmation"
                               v-model="passwordConfirmation"/>
                    </div>
                </div>

                <div class="error">{{error}}</div>

                <div class="button-field">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                login: "",
                name: "",
                password: "",
                passwordConfirmation: "",
                error: ""
            }
        },
        name: "Register",
        beforeCreate() {
            this.$root.$on("onError", (error) => {
                this.error = error;
            });
        },
        beforeMount() {
            this.login = this.name = this.password = this.passwordConfirmation = this.error = "";
        }, methods: {
            onRegister: function () {
                this.$root.$emit("onPost",
                    "/api/1/users/registration",
                    {
                        login: this.login,
                        name: this.name,
                        password: this.password,
                        passwordConfirmation: this.passwordConfirmation
                    },
                    response => {
                        localStorage.setItem("jwt", response.data);
                        this.$root.$emit("onJwt", response.data, true);
                    });
            }
        }
    }
</script>

<style scoped>

</style>
