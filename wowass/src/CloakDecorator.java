class CloakDecorator extends HeroDecorator {
    public CloakDecorator(Hero decoratedHero) {
        super(decoratedHero);
    }

    @Override
    public void equip() {
        super.equip();
        System.out.println("Equipping Cloak");
    }
}
