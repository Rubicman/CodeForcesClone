<template>
    <div class="middle">
        <aside>
            <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
        </aside>
        <main>
            <Index v-if="page === 'Index'" :posts="posts"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <WritePost v-if="page === 'WritePost'" :users="users"/>
            <Post v-if="page === 'Post'" :post="pageArgs[0]"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import SidebarPost from './SidebarPost';
    import Users from "./middle/Users";
    import WritePost from "./middle/WritePost";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ["posts", "users"],
        data: function () {
            return {
                page: "Index",
                pageArgs: null
            }
        },
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            }
        },
        components: {
            Index,
            Enter,
            Register,
            SidebarPost,
            Users,
            WritePost,
            Post
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page, ...args) => {
                this.page = page;
                this.pageArgs = args;
            });
        }
    }
</script>

<style scoped>

</style>
