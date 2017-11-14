extern long lrand48(void);
int rand(void) {
    return (int) lrand48();
}
