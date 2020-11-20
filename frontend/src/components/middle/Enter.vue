<template>
    <div class="enter form-box">
        <div class="header">Enter</div>
        <div class="body">
            <form @submit.prevent="onEnter">
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
                        <label for="password">Password</label>
                    </div>
                    <div class="value">
                        <input id="password" type="password" name="password" v-model="password"/>
                    </div>
                </div>

                <div class="error">{{error}}</div>

                <div class="button-field">
                    <input type="submit" value="Enter">
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
                password: "",
                error: ""
            }
        },
        name: "Enter",
        beforeCreate() {
            this.$root.$on("onError", (error) => {
                this.error = error;
            });
        },
        beforeMount() {
            this.login = "";
            this.password = "";
            this.error = "";
        }, methods: {
            onEnter: function () {
                this.$root.$emit("onPost",
                    "/api/1/jwt",
                    {
                        login: this.login,
                        password: this.password
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
