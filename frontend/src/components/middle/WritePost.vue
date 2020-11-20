<template>
    <div class="form">
        <div class="header">Write Post</div>
        <div class="body">
            <form @submit.prevent="onWritePost">
                <input type="hidden" name="action" value="writePost"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input autofocus id="title" name="title" v-model="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea v-model="text" id="text" name="text"/>
                    </div>
                </div>

                <div class="error">{{error}}</div>

                <div class="button-field">
                    <input type="submit" value="Write">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                title: "",
                text: "",
                error: ""
            }
        },
        name: "WritePost",
        beforeCreate() {
            this.$root.$on("onError", (error) => {
                this.error = error;
            });
        },
        beforeMount() {
            this.title = this.text = this.error = "";
        }, methods: {
            onWritePost: function () {
                this.$root.$emit("onPost",
                    "/api/1/posts/create",
                    {
                        title: this.title,
                        text: this.text
                    },
                    () => {
                        this.$root.$emit("onPostCreationSuccess")
                    });
            }
        }
    }
</script>

<style scoped>

</style>